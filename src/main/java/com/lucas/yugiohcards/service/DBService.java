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
public class DBService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private YgoProClient ygoProClient;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void importarTodoasCartas() {
        ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO = ygoProClient.buscarTodosCards();

        List<CartaMonstro> listaCartaMonstro = importacaoCartaYgoProDTO.getData().stream().map(c -> {
            CartaMonstro cartaMonstro = modelMapper.map(c, CartaMonstro.class);

            cartaMonstro.setStatusBanListGoat("Ilimitado");
            cartaMonstro.setStatusBanListTcg("Ilimitado");
            cartaMonstro.setStatusBanListOcg("Ilimitado");

            if(c.getMarcadorLink() != null && c.getMarcadorLink().size() > 0) {
                String marcadorLink = String.join(";", c.getMarcadorLink());

                cartaMonstro.setMarcadorLink(marcadorLink);
            }

            if(c.getBanListInfo() != null) {
                String banGoat = retornaStatusBanlist(c.getBanListInfo().getStatusGoat());
                String banTcg = retornaStatusBanlist(c.getBanListInfo().getStatusTcg());
                String banOcg = retornaStatusBanlist(c.getBanListInfo().getStatusOcg());

                cartaMonstro.setStatusBanListGoat(banGoat);
                cartaMonstro.setStatusBanListTcg(banTcg);
                cartaMonstro.setStatusBanListOcg(banOcg);
            }

            return cartaMonstro;
        }).collect(Collectors.toList());

        cartaMonstroRepository.saveAll(listaCartaMonstro);
    }

    private String retornaStatusBanlist(String status) {

        if(status == null) {
            return "Ilimitado";
        }

        switch (status) {
            case "Limited":
                status = "Limitado";
                break;
            case "Semi-Limited":
                status = "Semi-Limitado";
                break;
            case "Banned":
                status = "Banido";
                break;
        }

        return status;
    }

}
