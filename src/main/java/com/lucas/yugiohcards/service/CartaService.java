package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.client.ImportadorCartasClient;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.repository.CartaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaService {

    @Autowired
    private CartaRepository repository;

    @Autowired
    private ImportadorCartasClient importadorCartasClient;

    private ModelMapper modelMapper = new ModelMapper();

    public ImportacaoCartaResponse importarTodasCartas() throws Exception {
        try{
            ImportacaoCartaResponse cartasImportadas = importadorCartasClient.buscarTodasCartas();

            return cartasImportadas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
