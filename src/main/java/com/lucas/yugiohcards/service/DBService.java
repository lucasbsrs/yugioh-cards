package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.dto.ImportacaoCartaDTO;
import com.lucas.yugiohcards.model.CartaMonstro;
import com.lucas.yugiohcards.model.SetCarta;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import com.lucas.yugiohcards.repository.SetCartaRepository;
import com.lucas.yugiohcards.repository.TipoCartaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
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
    private SetCartaRepository setCartaRepository;

    @Autowired
    private WebClient webClientYgoPro;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ImportacaoCartaDTO importacaoCartas(String tipo) {

        Mono<ImportacaoCartaDTO> monoCards;
        if(tipo != null && !tipo.isEmpty()) {
            monoCards = webClientYgoPro
                    .method(HttpMethod.GET)
                    .uri("?type="+tipo+"num=1&offset=0")
                    .retrieve()
                    .bodyToMono(ImportacaoCartaDTO.class);
        } else {
            monoCards = webClientYgoPro
                    .method(HttpMethod.GET)
//                    .uri("?cardset=Metal Raiders&num=1&offset=0")
                    .retrieve()
                    .bodyToMono(ImportacaoCartaDTO.class);
        }

        ImportacaoCartaDTO importacaoCartaDTO = monoCards.block();

        List<CartaMonstro> listaCartaMonstro = importacaoCartaDTO.getData().stream().map(c -> {
            CartaMonstro cartaMonstro = modelMapper.map(c, CartaMonstro.class);

            return cartaMonstro;
        }).collect(Collectors.toList());

        cartaMonstroRepository.saveAll(listaCartaMonstro);

        return importacaoCartaDTO;
     }

}
