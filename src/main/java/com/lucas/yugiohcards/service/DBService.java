package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.CartaMonstroDTO;
import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.model.CartaMonstro;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import com.lucas.yugiohcards.repository.TipoCartaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private TipoCartaRepository tipoCartaRepository;

    @Autowired
    private WebClient webClientYgoPro;

    private ModelMapper modelMapper = new ModelMapper();

    public CartaMonstroDTO importacaoCartas(String tipo) {

        Mono<CartaMonstroDTO> monoCards;
        if(tipo != null && !tipo.isEmpty()) {
            monoCards = webClientYgoPro
                    .method(HttpMethod.GET)
                    .uri("?type="+tipo)
                    .retrieve()
                    .bodyToMono(CartaMonstroDTO.class);
        } else {
            monoCards = webClientYgoPro
                    .method(HttpMethod.GET)
                    .retrieve()
                    .bodyToMono(CartaMonstroDTO.class);
        }


        CartaMonstroDTO cartaMonstroDTO = monoCards.block();

        List<CartaMonstro> listaCartaMonstro = cartaMonstroDTO.getData().stream().map(c -> {
            CartaMonstro cartaMonstro = modelMapper.map(c, CartaMonstro.class);

            return cartaMonstro;
        }).collect(Collectors.toList());

        cartaMonstroRepository.saveAll(listaCartaMonstro);

        return cartaMonstroDTO;
     }

}
