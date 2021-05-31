package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.dto.ImportacaoCartaYgoProDTO;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaMonstroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportacaoCardsService {

    @Autowired
    private CartaMonstroRepository cartaMonstroRepository;

    @Autowired
    private WebClient webClientYgoPro;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void importarTodasCartas() {

        Mono<ImportacaoCartaYgoProDTO> monoCards;
        monoCards = webClientYgoPro
                .method(HttpMethod.GET)
//                .uri("?cardset=Metal Raiders&num=1&offset=0")
                .retrieve()
                .bodyToMono(ImportacaoCartaYgoProDTO.class);

        ImportacaoCartaYgoProDTO importacaoCartaYgoProDTO = monoCards.block();

        List<Carta> listaCarta = importacaoCartaYgoProDTO.getData().stream().map(c -> {
            Carta carta = modelMapper.map(c, Carta.class);

            if(c.getMarcadorLink() != null && c.getMarcadorLink().size() > 0) {
                String marcadorLink = String.join(";", c.getMarcadorLink());

                carta.setMarcadorLink(marcadorLink);
            }

            if(c.getBanListInfo() != null) {
                carta.setStatusBanListGoat(c.getBanListInfo().getStatusGoat());
                carta.setStatusBanListTcg(c.getBanListInfo().getStatusTcg());
                carta.setStatusBanListOcg(c.getBanListInfo().getStatusOcg());
            }

            return carta;
        }).collect(Collectors.toList());

        cartaMonstroRepository.saveAll(listaCarta);
    }

}
