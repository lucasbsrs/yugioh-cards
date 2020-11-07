package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import com.lucas.yugiohcards.repository.TipoCartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private TipoCartaRepository tipoCartaRepository;

    @Autowired
    private YgoProClient ygoProClient;

    public void importacaoCartas() {
        List<CartaMonstroDTO> lista = ygoProClient.buscarTodosCards();

        List<CartaMonstroDTO> lista2 = lista;
    }

}
