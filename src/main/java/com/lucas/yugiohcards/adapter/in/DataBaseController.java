package com.lucas.yugiohcards.adapter.in;

import com.lucas.yugiohcards.application.out.ImportarTodasCartasOutPort;
import com.lucas.yugiohcards.application.usecase.DataBaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/database")
public class DataBaseController {

    private DataBaseUseCase dataBaseUseCase;

    private ImportarTodasCartasOutPort importarTodasCartasOutPort;

	@PostMapping("/importar-cartas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> importarTodasCartas() throws Exception {
        importarTodasCartasOutPort.importarTodasCartas();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar-codigos")
    public ResponseEntity<?> atualizarCodigos() {

        dataBaseUseCase.atualizarCodigosCartas();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar-nomes-cartas")
    public ResponseEntity<?> atualizarNomeCartas() {

        dataBaseUseCase.atualizarNomeCartas();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar-novas-cartas")
    public ResponseEntity<?> atualizarNovasCartas() {

        dataBaseUseCase.atualizarNovasCartas();

        return ResponseEntity.noContent().build();
    }

}
