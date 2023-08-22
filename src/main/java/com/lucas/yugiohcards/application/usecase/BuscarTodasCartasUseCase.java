package com.lucas.yugiohcards.application.usecase;

import com.lucas.yugiohcards.application.factory.CartaFactory;
import com.lucas.yugiohcards.application.out.BuscarTodasCartasOutPort;
import com.lucas.yugiohcards.application.domains.CartaRecord;
import com.lucas.yugiohcards.adapter.out.postgressql.entities.Carta;
import com.lucas.yugiohcards.adapter.out.postgressql.repositories.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BuscarTodasCartasUseCase implements BuscarTodasCartasOutPort {

    private CartaRepository repository;

    public List<CartaRecord> buscarTodasCartas() {
        List<Carta> cartas = repository.findAll();

        return cartas.stream().map(CartaFactory::toCartaRecord).toList();
    }

}
