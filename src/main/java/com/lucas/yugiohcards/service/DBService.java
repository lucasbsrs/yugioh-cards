package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.enums.AtributoEnum;
import com.lucas.yugiohcards.enums.StatusCartaEnum;
import com.lucas.yugiohcards.model.CartaMonstro;
import com.lucas.yugiohcards.model.TipoCarta;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import com.lucas.yugiohcards.repository.TipoCartaRepository;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private TipoCartaRepository tipoCartaRepository;

    public void instantiateTestDatabase()  {

        List<TipoCarta> listTipoCarta = new ArrayList<>();

        TipoCarta tc1 = TipoCarta.builder().id(1L).descricao("Winged Beast").build();
        TipoCarta tc2 = TipoCarta.builder().id(2L).descricao("Efeito").build();

        tipoCartaRepository.saveAll(Arrays.asList(tc1, tc2));

        listTipoCarta.addAll(Arrays.asList(tc1, tc2));

        CartaMonstro m1 = CartaMonstro.builder()
                                      .id(1L)
                                      .nome("Dragunity Dux")
                                      .numero("LCKC-EN084")
                                      .descricao("Gains 200 ATK for each \"Dragunity\" card you control. When this card is Normal Summoned: You can target 1 Level 3 or lower Dragon \"Dragunity\" monster in your GY; equip that target to this card.")
                                      .status(StatusCartaEnum.UNLIMITED)
                                      .atributo(AtributoEnum.WIND)
                                      .level(4L)
                                      .tipoCartas(listTipoCarta)
                                      .ataque(1500.0)
                                      .defesa(1000.0)
                                      .build();

        cartaMonstroRepository.save(m1);
    }
}
