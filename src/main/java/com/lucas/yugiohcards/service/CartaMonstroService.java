package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.model.CartaMonstro;
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
        List<CartaMonstro> listaCartaMonstro = repository.findAll();

        return listaCartaMonstro.stream().map(c -> {
            CartaMonstroDTO cartaMonstroDTO = modelMapper.map(c, CartaMonstroDTO.class);

            return cartaMonstroDTO;
        }).collect(Collectors.toList());

    }

    public CartaMonstroDTO buscarCartaPorNome(String nomeCarta) {
        CartaMonstroDTO cartaMonstroDTO = new CartaMonstroDTO();

        CartaMonstro cartaMonstro = repository.findByNomeEquals(nomeCarta);

        if(cartaMonstro != null) {
            cartaMonstroDTO = modelMapper.map(cartaMonstro, CartaMonstroDTO.class);
        }

        return cartaMonstroDTO;
    }

    public CartaMonstro save(CartaMonstro monsterCard){
        return repository.save(monsterCard);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    
}
