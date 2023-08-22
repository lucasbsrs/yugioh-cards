package com.lucas.yugiohcards.application.out;

import com.lucas.yugiohcards.application.domains.CartaRecord;


public interface BuscarCartaPorIdOutPort {

    CartaRecord buscarPorId(Long id);

}
