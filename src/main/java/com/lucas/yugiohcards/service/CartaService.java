package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.domains.ChangeLogDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.ChangeLogResponse;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartaService {

    @Autowired
    private CartaRepository repository;

    @Autowired
    private YgoProClient ygoProClient;

    private ModelMapper modelMapper = new ModelMapper();

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final Long TAMANHO_LISTA_CHANGE_LOG = 10L;

    public void importarTodasCartas() throws Exception {
        try{
            ImportacaoCartaResponse cartasImportadas = ygoProClient.buscarTodasCartas();

            List<Carta> listaCartas = cartasImportadas.getData().stream().map(c -> {

                Carta carta = modelMapper.map(c, Carta.class);

                if(c.getMarcadorLink() != null && !c.getMarcadorLink().isEmpty()) {
                    String marcadorLink = c.getMarcadorLink().stream().map(m -> String.valueOf(m)).collect(Collectors.joining(";"));
                    carta.setMarcadorLink(marcadorLink);
                }

                return carta;
            }).collect(Collectors.toList());

            repository.saveAll(listaCartas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Carta> atualizarCodigosCartas() {

        ChangeLogResponse changeLogResponse = ygoProClient.consultarChangeLogId();

        List<ChangeLogDTO> listaChangeLogAtualizar = changeLogResponse.getData().stream()
                .skip(Math.max(0, changeLogResponse.getData().size() - TAMANHO_LISTA_CHANGE_LOG))
                .map(x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(x.getDate(), formatter);

                    return ChangeLogDTO
                            .builder()
                            .nome(x.getNome())
                            .dataAtualizacao(dateTime)
                            .oldId(x.getOldId())
                            .newId(x.getNewId())
                            .build();

                })
                .filter(c -> c.getDataAtualizacao().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        List<String> listaChangeLogCodigos = listaChangeLogAtualizar.stream().map(ChangeLogDTO::getOldId).collect(Collectors.toList());

        List<Carta> cartas = repository.findByCodigoIn(listaChangeLogCodigos);

        cartas.stream().forEach(carta -> {
            ChangeLogDTO changeLogAtualizarRetorno = listaChangeLogAtualizar.stream().filter(x -> x.getOldId() == carta.getCodigo()).findFirst().get();

            carta.setCodigo(changeLogAtualizarRetorno.getNewId());
        });

        cartas = repository.saveAll(cartas);

        return cartas;
    }
    
}
