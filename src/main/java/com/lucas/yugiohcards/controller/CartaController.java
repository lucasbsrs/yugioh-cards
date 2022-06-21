package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.domains.CartaRecord;
import com.lucas.yugiohcards.domains.PessoaDTO;
import com.lucas.yugiohcards.model.Pessoa;
import com.lucas.yugiohcards.repository.PessoaRepository;
import com.lucas.yugiohcards.service.CartaService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/{id}")
    public ResponseEntity<CartaRecord> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cartaService.buscarPorId(id));
    }

	@PostMapping("/importar-cartas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> importarTodasCartas() throws Exception {
        cartaService.importarTodasCartas();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar-codigos")
    public ResponseEntity<?> atualizarCodigos() {

        cartaService.atualizarCodigosCartas();

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizar-pessoa/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {


        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        Pessoa pessoa = pessoaRepository.findById(id).get();

        PropertyMap<PessoaDTO, Pessoa> pessoaMap = new PropertyMap<PessoaDTO, Pessoa>() {
            protected void configure() {
                map().setEndereco(source.getEndCompleto());
                map().setCidade(source.getCi());
            }
        };

        modelMapper.addMappings(pessoaMap);
        modelMapper.map(pessoaDTO, pessoa);

        pessoaRepository.save(pessoa);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultar-pessoa/{id}")
    public ResponseEntity<?> consultarPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaRepository.findById(id));
    }

}
