package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.domains.ChangeLogIdDTO;
import com.lucas.yugiohcards.domains.ChangeLogNameDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.ChangeLogIdResponse;
import com.lucas.yugiohcards.integrations.response.ChangeLogNameResponse;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DataBaseService {

    private CartaRepository repository;

    private YgoProClient ygoProClient;

    private final Long TAMANHO_LISTA_CHANGE_LOG = 10L;

    public void importarTodasCartas() throws Exception {
        try{
            ModelMapper modelMapper = new ModelMapper();

            ImportacaoCartaResponse cartasImportadas = ygoProClient.buscarTodasCartas();

            List<Carta> listaCartas = cartasImportadas.getData().stream().map(c -> {

                Carta carta = modelMapper.map(c, Carta.class);

                if(c.getMarcadorLink() != null && !c.getMarcadorLink().isEmpty()) {
                    String marcadorLink = c.getMarcadorLink().stream().map(String::valueOf).collect(Collectors.joining(";"));
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

        ChangeLogIdResponse changeLogIdResponse = ygoProClient.consultarChangeLogId();

        List<Carta> cartas = new ArrayList<>();

        List<ChangeLogIdDTO> listaChangeLogAtualizar = changeLogIdResponse.getData().stream()
                .skip(Math.max(0, changeLogIdResponse.getData().size() - TAMANHO_LISTA_CHANGE_LOG))
                .map(x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(x.getDate(), formatter);

                    return ChangeLogIdDTO
                            .builder()
                            .nome(x.getNome())
                            .dataAtualizacao(dateTime)
                            .oldId(x.getOldId())
                            .newId(x.getNewId())
                            .build();

                })
                .filter(c -> c.getDataAtualizacao().isAfter(LocalDateTime.now())).toList();

        if(!listaChangeLogAtualizar.isEmpty()) {
            List<String> listaChangeLogCodigos = listaChangeLogAtualizar.stream().map(ChangeLogIdDTO::getOldId).collect(Collectors.toList());

            cartas = repository.findByCodigoIn(listaChangeLogCodigos);

            cartas.stream().forEach(carta -> {
                ChangeLogIdDTO changeLogAtualizarRetorno = listaChangeLogAtualizar.stream().filter(x -> x.getOldId() == carta.getCodigo()).findFirst().get();

                carta.setCodigo(changeLogAtualizarRetorno.getNewId());
            });

            cartas = repository.saveAll(cartas);
        }

        return cartas;
    }

    public List<Carta> atualizarNomeCartas() {

        ChangeLogNameResponse changeLogNameResponse = ygoProClient.consultarChangeLogName();

        List<ChangeLogNameDTO> listaChangeLogNameResponse = changeLogNameResponse.getData().stream()
                .limit(TAMANHO_LISTA_CHANGE_LOG)
                .map(x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(x.getDataAtualizacao(), formatter);

                    return ChangeLogNameDTO
                            .builder()
                            .dataAtualizacao(dateTime)
                            .nomeAntigo(x.getNomeAntigo())
                            .nomeNovo(x.getNomeNovo())
                            .build();

                }).toList();

        System.out.println(listaChangeLogNameResponse);

        return null;
    }
}
