package com.lucas.yugiohcards.adapters;

import com.lucas.yugiohcards.domains.CartaRecord;
import com.lucas.yugiohcards.integrations.response.ImportacaoCartaResponse;
import com.lucas.yugiohcards.model.Carta;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartaAdapter {

    static ModelMapper modelMapper = new ModelMapper();

    public static CartaRecord toCartaRecord(Carta carta) {
        return new CartaRecord(carta.getId(), carta.getCodigo(), carta.getNome(), carta.getTipo(), carta.getDescricao(),
                carta.getAtaque(), carta.getDefesa(), carta.getLevel(), carta.getRaca(), carta.getAtributo(),
                carta.getArquetipo(), carta.getEscala(), carta.getLinkValor(), carta.getMarcadorLink(),
                carta.getStatusBanListTcg(), carta.getStatusBanListOcg(), null, null);
    }

    public static List<Carta> importacaoCartaResponseToCarta(ImportacaoCartaResponse cartasImportadas) {
        return cartasImportadas.getData().stream().map(c -> {

            Carta carta = modelMapper.map(c, Carta.class);

            if (c.getMarcadorLink() != null && !c.getMarcadorLink().isEmpty()) {
                String marcadorLink = c.getMarcadorLink().stream().map(String::valueOf).collect(Collectors.joining(";"));
                carta.setMarcadorLink(marcadorLink);
            }

            carta.setDataCriacao(LocalDateTime.now());

            return carta;
        }).collect(Collectors.toList());
    }

}
