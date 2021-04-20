package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.dto.ImportacaoCartaYgoProDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.model.CartaMonstro;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportacaoCardsService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private YgoProClient ygoProClient;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void importarTodasCartas() {
        ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO = ygoProClient.buscarTodosCards();

        List<CartaMonstro> listaCartaMonstro = importacaoCartaYgoProDTO.getData().stream().map(c -> {
            CartaMonstro cartaMonstro = modelMapper.map(c, CartaMonstro.class);

            if(c.getMarcadorLink() != null && c.getMarcadorLink().size() > 0) {
                String marcadorLink = String.join(";", c.getMarcadorLink());

                cartaMonstro.setMarcadorLink(marcadorLink);
            }

            if(c.getBanListInfo() != null) {
                cartaMonstro.setStatusBanListGoat(c.getBanListInfo().getStatusGoat());
                cartaMonstro.setStatusBanListTcg(c.getBanListInfo().getStatusTcg());
                cartaMonstro.setStatusBanListOcg(c.getBanListInfo().getStatusOcg());
            }

            return cartaMonstro;
        }).collect(Collectors.toList());

        cartaMonstroRepository.saveAll(listaCartaMonstro);
    }

}
