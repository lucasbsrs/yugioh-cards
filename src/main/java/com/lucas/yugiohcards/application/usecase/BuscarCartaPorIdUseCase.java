package com.lucas.yugiohcards.application.usecase;

import com.lucas.yugiohcards.application.exceptions.NotFoundException;
import com.lucas.yugiohcards.application.factory.CartaFactory;
import com.lucas.yugiohcards.application.out.BuscarCartaPorIdOutPort;
import com.lucas.yugiohcards.application.domains.CartaRecord;
import com.lucas.yugiohcards.adapter.out.postgressql.entities.Carta;
import com.lucas.yugiohcards.adapter.out.postgressql.repositories.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BuscarCartaPorIdUseCase implements BuscarCartaPorIdOutPort {

    private CartaRepository repository;

    public CartaRecord buscarPorId(Long id) {
        Carta carta = repository.findById(id).orElseThrow(() -> new NotFoundException("Carta não encontrada"));

        return CartaFactory.toCartaRecord(carta);
    }

}
