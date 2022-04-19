package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.domains.ChangeLogDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.ChangeLogResponse;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaService {

    @Autowired
    private CartaRepository repository;

    @Autowired
    private YgoProClient ygoProClient;

    private ModelMapper modelMapper = new ModelMapper();

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

    public void atualizarCodigosCartas() {

//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTeste = LocalDateTime.parse("2022-02-19 06:46:28", formatter2);

        ChangeLogResponse changeLogResponse = ygoProClient.consultarChangeLogId();

        List<ChangeLogDTO> listaChangeLogAtualizar = changeLogResponse.getData().stream()
                .skip(Math.max(0, changeLogResponse.getData().size() - 10))
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

        List<String> listaChangeLogCodigos = listaChangeLogAtualizar.stream().map(ChangeLogDTO::getNewId).collect(Collectors.toList());

        List<Carta> cartas = repository.findByCodigoIn(listaChangeLogCodigos);

        System.out.println(cartas);

    }
    
}
