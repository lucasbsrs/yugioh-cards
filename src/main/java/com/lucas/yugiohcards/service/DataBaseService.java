package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.adapters.CartaAdapter;
import com.lucas.yugiohcards.domains.ChangeLogIdDTO;
import com.lucas.yugiohcards.domains.ChangeLogNameDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.*;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private DataBaseVersionService dataBaseVersionService;

    private final Long DIAS_ATUALIZACAO = 10L;

    @Transactional
    public void importarTodasCartas() throws Exception {
        try{
            ImportacaoCartaResponse cartasImportadas = ygoProClient.buscarTodasCartas();

            List<CheckDatabaseVersionResponse> dataBaseVersionYgoPro = consultarVersaoDataBase();

            List<Carta> listaCartas = CartaAdapter.importacaoCartaResponseToCarta(cartasImportadas);

            dataBaseVersionService.atualizar(dataBaseVersionYgoPro.get(0).getDataBaseVersion());

            repository.saveAll(listaCartas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Carta> atualizarCodigosCartas() {

        List<Carta> cartas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ChangeLogIdResponse changeLogIdResponse = ygoProClient.consultarChangeLogId();

        List<CheckDatabaseVersionResponse> versaoDataBase = consultarVersaoDataBase();

        LocalDateTime dateUpdateDataBaseVersion = LocalDateTime.parse(versaoDataBase.get(0).getLastUpdate(), formatter);

        List<ChangeLogIdDTO> listaChangeLogAtualizar = changeLogIdResponse.getData().stream()
                .filter(p -> LocalDateTime.parse(p.getDate(), formatter).isAfter(dateUpdateDataBaseVersion.minusDays(DIAS_ATUALIZACAO)))
                .map(x -> {
                    LocalDateTime dateTime = LocalDateTime.parse(x.getDate(), formatter);

                    return ChangeLogIdDTO
                            .builder()
                            .nome(x.getNome())
                            .dataAtualizacao(dateTime)
                            .oldId(x.getOldId())
                            .newId(x.getNewId())
                            .build();

                }).toList();

        if(!listaChangeLogAtualizar.isEmpty()) {
            List<String> listaChangeLogCodigos = listaChangeLogAtualizar.stream().map(ChangeLogIdDTO::getOldId).collect(Collectors.toList());

            cartas = repository.findByCodigoIn(listaChangeLogCodigos);

            cartas.forEach(carta -> {
                ChangeLogIdDTO changeLogAtualizarRetorno = listaChangeLogAtualizar.stream().filter(x -> x.getOldId() == carta.getCodigo()).findFirst().get();

                carta.setCodigo(changeLogAtualizarRetorno.getNewId());
            });

            cartas = repository.saveAll(cartas);
        }

        return cartas;
    }

    public List<Carta> atualizarNomeCartas() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Carta> cartas = new ArrayList<>();

        ChangeLogNameResponse changeLogNameResponse = ygoProClient.consultarChangeLogName();

        List<CheckDatabaseVersionResponse> checkDatabaseVersionResponse = consultarVersaoDataBase();

        LocalDateTime dateUpdateDataBaseVersion = LocalDateTime.parse(checkDatabaseVersionResponse.get(0).getLastUpdate(), formatter);

        List<ChangeLogNameDTO> listaChangeLogNameResponse = changeLogNameResponse.getData().stream()
                .filter(p -> LocalDateTime.parse(p.getDataAtualizacao(), formatter).isAfter(dateUpdateDataBaseVersion.minusDays(DIAS_ATUALIZACAO)))
                .map(x -> {
                    LocalDateTime dateTime = LocalDateTime.parse(x.getDataAtualizacao(), formatter);

                    return ChangeLogNameDTO
                            .builder()
                            .dataAtualizacao(dateTime)
                            .nomeAntigo(x.getNomeAntigo())
                            .nomeNovo(x.getNomeNovo())
                            .build();

                }).toList();

        if(!listaChangeLogNameResponse.isEmpty()) {
            List<String> listaChangeLogNomes = listaChangeLogNameResponse.stream()
                    .map(ChangeLogNameDTO::getNomeAntigo)
                    .collect(Collectors.toList());

            cartas = repository.findByNomeIn(listaChangeLogNomes);

            if(!cartas.isEmpty()) {
                cartas.forEach(carta -> {
                    ChangeLogNameDTO changeLogAtualizarRetorno = listaChangeLogNameResponse.stream()
                            .filter(x -> x.getNomeAntigo() == carta.getNome())
                            .findFirst().get();

                    carta.setNome(changeLogAtualizarRetorno.getNomeNovo());
                });

                cartas = repository.saveAll(cartas);
            }
        }

        return cartas;
    }

    public void atualizarNovasCartas() {

        ImportacaoCartaResponse importacaoCartaResponse = ygoProClient.buscarTodasCartas();

        List<Carta> cartasBaseDado = repository.findAll();

        List<String> codigosCartasBaseDado = cartasBaseDado.stream().map(Carta::getCodigo).toList();

        List<String> codigosCartasYgoPro = importacaoCartaResponse.getData().stream().map(DadosCartaMonstroResponse::getCodigo).toList();

        List<String> nonExistentRecords = new ArrayList<>();

        for (String record : codigosCartasYgoPro) {
            if (!codigosCartasBaseDado.contains(record)) {
                nonExistentRecords.add(record);
            }
        }

        ImportacaoCartaResponse novasCartasFiltradas = new ImportacaoCartaResponse();

        novasCartasFiltradas.setData(importacaoCartaResponse.getData().stream()
                .filter(e -> nonExistentRecords.contains(e.getCodigo()))
                .collect(Collectors.toList()));

        List<Carta> listaCartas = CartaAdapter.importacaoCartaResponseToCarta(novasCartasFiltradas);

        repository.saveAll(listaCartas);
    }

    private List<CheckDatabaseVersionResponse> consultarVersaoDataBase() {
        return ygoProClient.consultarVersaoDataBase();
    }
}
