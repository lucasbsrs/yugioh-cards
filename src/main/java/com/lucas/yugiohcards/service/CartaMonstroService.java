package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
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
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private DBService dbService;

    private ModelMapper model = new ModelMapper();

    public List<CartaMonstroDTO> findAll() {
//        List<CartaMonstro> listaCartaMonstros = cartaMonstroRepository.findAll();
//
//        return listaCartaMonstros.stream()
//                                 .map(item -> model.map(item, CartaMonstroDTO.class))
//                                 .collect(Collectors.toList());

        dbService.importacaoCartas();

        return null;
    }

    public CartaMonstro save(CartaMonstro monsterCard){
        return cartaMonstroRepository.save(monsterCard);
    }

    public void delete(Long id){
        cartaMonstroRepository.deleteById(id);
    }
    
}
