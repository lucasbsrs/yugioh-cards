package com.lucas.yugiohcards.adapters;

import com.lucas.yugiohcards.domains.CartaRecord;
import com.lucas.yugiohcards.model.Carta;

public class CartaAdapter {

    public static CartaRecord toCartaRecord(Carta carta) {
        return new CartaRecord(carta.getId(), carta.getCodigo(), carta.getNome(), carta.getTipo(), carta.getDescricao(),
                carta.getAtaque(), carta.getDefesa(), carta.getLevel(), carta.getRaca(), carta.getAtributo(),
                carta.getArquetipo(), carta.getEscala(), carta.getLinkValor(), carta.getMarcadorLink(),
                carta.getStatusBanListTcg(), carta.getStatusBanListOcg(), carta.getSetsCarta(),
                carta.getImagensCarta());
    }

}
