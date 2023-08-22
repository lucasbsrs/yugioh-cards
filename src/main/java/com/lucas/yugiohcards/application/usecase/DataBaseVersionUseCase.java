package com.lucas.yugiohcards.application.usecase;

import com.lucas.yugiohcards.adapter.out.ygoclient.YgoProClient;
import com.lucas.yugiohcards.adapter.out.ygoclient.response.CheckDatabaseVersionResponse;
import com.lucas.yugiohcards.adapter.out.postgressql.entities.DataBaseVersion;
import com.lucas.yugiohcards.adapter.out.postgressql.repositories.DataBaseVersionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DataBaseVersionUseCase {

    private DataBaseVersionRepository repository;

    private YgoProClient ygoProClient;

    @Transactional
    public void atualizar(String versao, String dataAtualizacaoYgo) throws Exception {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            List<DataBaseVersion> listaDataBaseVersion = repository.findAll();

            DataBaseVersion dataBaseVersion = listaDataBaseVersion.size() > 0 ? listaDataBaseVersion.get(0) : new DataBaseVersion();

            dataBaseVersion.setVersao(BigDecimal.valueOf(Double.parseDouble(versao)));
            dataBaseVersion.setDataAtualizacao(LocalDateTime.now());
            dataBaseVersion.setDataAtualizacaoYgoPro(LocalDateTime.parse(dataAtualizacaoYgo, formatter));

            repository.save(dataBaseVersion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public DataBaseVersion consultarDataBaseBD() {
        return repository.findAll().get(0);
    }

    public CheckDatabaseVersionResponse consultarVersaoDataBaseYgoPro() {
        return ygoProClient.consultarVersaoDataBase().get(0);
    }

}
