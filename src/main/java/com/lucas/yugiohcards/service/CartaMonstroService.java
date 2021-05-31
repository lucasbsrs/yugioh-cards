package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaMonstroService {

    @Autowired
    private CartaMonstroRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<CartaMonstroDTO> findAll() {
        List<Carta> listaCarta = repository.findAll();

        return listaCarta.stream().map(c -> {
            CartaMonstroDTO cartaMonstroDTO = modelMapper.map(c, CartaMonstroDTO.class);

            return cartaMonstroDTO;
        }).collect(Collectors.toList());

    }

    public CartaMonstroDTO buscarCartaPorNome(String nomeCarta) {
        CartaMonstroDTO cartaMonstroDTO = new CartaMonstroDTO();

        Carta carta = repository.findByNomeEquals(nomeCarta);

        if(carta != null) {
            cartaMonstroDTO = modelMapper.map(carta, CartaMonstroDTO.class);
        }

        return cartaMonstroDTO;
    }

    public Carta save(Carta monsterCard){
        return repository.save(monsterCard);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    
}
