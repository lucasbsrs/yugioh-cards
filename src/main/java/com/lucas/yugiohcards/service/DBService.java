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
                    .uri("?type="+tipo)
                    .retrieve()
                    .bodyToMono(ImportacaoCartaDTO.class);
        } else {
            monoCards = webClientYgoPro
                    .method(HttpMethod.GET)
                    .retrieve()
                    .bodyToMono(ImportacaoCartaDTO.class);
        }


        ImportacaoCartaDTO importacaoCartaDTO = monoCards.block();

        List<CartaMonstro> listaCartaMonstro = importacaoCartaDTO.getData().stream().map(c -> {
            CartaMonstro cartaMonstro = modelMapper.map(c, CartaMonstro.class);
            List<SetCarta> listaSetCarta = new ArrayList<>();


//            if(c.getBanListInfo() != null) {
//                cartaMonstro.setStatusBanListTcg(c.getBanListInfo().getStatusTcg());
//                cartaMonstro.setStatusBanListOcg(c.getBanListInfo().getStatusOcg());
//                cartaMonstro.setStatusBanListGoat(c.getBanListInfo().getStatusGoat());
//            }

            CartaMonstro cartaMonstroSalva = cartaMonstroRepository.save(cartaMonstro);
            if(c.getSetCarta() != null) {
                listaSetCarta = c.getSetCarta().stream().map(sc -> {
                    SetCarta setCarta = modelMapper.map(sc, SetCarta.class);
                    setCarta.setCartaMonstro(cartaMonstroSalva);
                    return setCarta;
                }).collect(Collectors.toList());
                setCartaRepository.saveAll(listaSetCarta);
//                cartaMonstro.setSetCartas(listaSetCarta);
            }

            return cartaMonstro;
        }).collect(Collectors.toList());

//        cartaMonstroRepository.saveAll(listaCartaMonstro);

        return importacaoCartaDTO;
     }

}
