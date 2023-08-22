package com.lucas.yugiohcards.adapter.in;

import com.lucas.yugiohcards.application.out.BuscarCartaPorIdOutPort;
import com.lucas.yugiohcards.application.out.BuscarTodasCartasOutPort;
import com.lucas.yugiohcards.application.domains.CartaRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cartas")
public class CartaController {

    private BuscarTodasCartasOutPort buscarTodasCartasOutPort;

    private BuscarCartaPorIdOutPort buscarCartaPorIdOutPort;

    @GetMapping
    public ResponseEntity<List<CartaRecord>> buscarTodasCartas() {
        return ResponseEntity.ok(buscarTodasCartasOutPort.buscarTodasCartas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaRecord> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarCartaPorIdOutPort.buscarPorId(id));
    }

}
