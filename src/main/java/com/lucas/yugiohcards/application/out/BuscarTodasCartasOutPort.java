package com.lucas.yugiohcards.application.out;

import com.lucas.yugiohcards.application.domains.CartaRecord;

import java.util.List;

public interface BuscarTodasCartasOutPort {

    List<CartaRecord> buscarTodasCartas();

}
