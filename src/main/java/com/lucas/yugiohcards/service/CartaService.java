package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.client.ImportadorCartasClient;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaService {

    @Autowired
    private CartaRepository repository;

    @Autowired
    private ImportadorCartasClient importadorCartasClient;

    private ModelMapper modelMapper = new ModelMapper();

    public void importarTodasCartas() throws Exception {
        try{
            ImportacaoCartaResponse cartasImportadas = importadorCartasClient.buscarTodasCartas();

            List<Carta> listaCartas = cartasImportadas.getData().stream().map(c -> {

                Carta carta = modelMapper.map(c, Carta.class);

                if(c.getMarcadorLink() != null && !c.getMarcadorLink().isEmpty()) {
                    String marcadorLink = c.getMarcadorLink().stream().map(m -> String.valueOf(m)).collect(Collectors.joining(";"));
                    carta.setMarcadorLink(marcadorLink);
                }

                return carta;
            }).collect(Collectors.toList());

            repository.saveAll(listaCartas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
