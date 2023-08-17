package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.CheckDatabaseVersionResponse;
import com.lucas.yugiohcards.model.DataBaseVersion;
import com.lucas.yugiohcards.repository.DataBaseVersionRepository;
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
public class DataBaseVersionService {

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
