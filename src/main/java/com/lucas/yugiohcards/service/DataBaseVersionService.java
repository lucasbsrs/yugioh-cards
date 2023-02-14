package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.model.DataBaseVersion;
import com.lucas.yugiohcards.repository.DataBaseVersionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DataBaseVersionService {

    private DataBaseVersionRepository repository;

    @Transactional
    public void atualizar(String versao) throws Exception {
        try{
            List<DataBaseVersion> listaDataBaseVersion = repository.findAll();

            DataBaseVersion dataBaseVersion = listaDataBaseVersion.size() > 0 ? listaDataBaseVersion.get(0) : new DataBaseVersion();

            dataBaseVersion.setVersao(versao);
            dataBaseVersion.setDataAtualizacao(LocalDateTime.now());

            repository.save(dataBaseVersion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
