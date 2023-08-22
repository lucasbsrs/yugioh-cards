package com.lucas.yugiohcards.application.usecase;

import com.lucas.yugiohcards.adapter.out.ygoclient.YgoProClient;
import com.lucas.yugiohcards.application.factory.CartaFactory;
import com.lucas.yugiohcards.application.out.ImportarTodasCartasOutPort;
import com.lucas.yugiohcards.adapter.out.ygoclient.response.CheckDatabaseVersionResponse;
import com.lucas.yugiohcards.adapter.out.ygoclient.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.adapter.out.postgressql.entities.Carta;
import com.lucas.yugiohcards.adapter.out.postgressql.repositories.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ImportarTodasCartasUseCase implements ImportarTodasCartasOutPort {

    private CartaRepository repository;

    private YgoProClient ygoProClient;

    private DataBaseVersionUseCase dataBaseVersionUseCase;

    @Transactional
    public void importarTodasCartas() throws Exception {
        try{
            ImportacaoCartaResponse cartasImportadas = ygoProClient.buscarTodasCartas();

            CheckDatabaseVersionResponse dataBaseVersionYgoPro = dataBaseVersionUseCase.consultarVersaoDataBaseYgoPro();

            List<Carta> listaCartas = CartaFactory.importacaoCartaResponseToCarta(cartasImportadas);

            dataBaseVersionUseCase.atualizar(dataBaseVersionYgoPro.getDataBaseVersion(), dataBaseVersionYgoPro.getLastUpdate());

            repository.saveAll(listaCartas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
