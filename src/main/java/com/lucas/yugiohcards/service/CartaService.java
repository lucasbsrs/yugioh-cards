package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.adapters.CartaAdapter;
import com.lucas.yugiohcards.domains.CartaRecord;
import com.lucas.yugiohcards.exceptions.NotFoundException;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CartaService {

    private CartaRepository repository;

    public CartaRecord buscarPorId(Long id) {
        Carta carta = repository.findById(id).orElseThrow(() -> new NotFoundException("Carta não encontrada"));

        return CartaAdapter.toCartaRecord(carta);
    }

    public List<CartaRecord> buscarTodasCartas() {
        List<Carta> cartas = repository.findAll();

        return cartas.stream().map(CartaAdapter::toCartaRecord).toList();
    }

}
