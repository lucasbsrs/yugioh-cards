package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.ChangeLogResponse;
import com.lucas.yugiohcards.integrations.response.DadosChangeLogResponse;
import com.lucas.yugiohcards.model.Carta;
import com.lucas.yugiohcards.repository.CartaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CartaServiceTest {

    @InjectMocks
    private CartaService cartaService;

    @Mock
    private YgoProClient ygoProClient;

    @Mock
    private CartaRepository repository;

    @Test
    @DisplayName("Deve atualizar código da carta caso algum registro do ChangeLogID seja maior que a data atual")
    void deveAtualizarCodigoCartaPorChangeLogID() {

        String codigoAntigo = "1111";
        String codigoNovo = "2222";
        LocalDateTime dataAtualMaisUm = LocalDateTime.now().plusDays(1);

        Carta carta = criarCarta(codigoAntigo, "Carta Teste");
        Carta cartaAtualizada = criarCarta(codigoNovo, "Carta Teste");

        ChangeLogResponse changeLogResponse = criarChangeLogResponse(codigoNovo, codigoAntigo, dataAtualMaisUm);

        List<String> listaCodigosAntigos = Arrays.asList(codigoAntigo);
        List<Carta> listaCartas = Arrays.asList(carta);
        List<Carta> listaCartasAtualizadas = Arrays.asList(cartaAtualizada);

        when(ygoProClient.consultarChangeLogId()).thenReturn(changeLogResponse);
        when(repository.findByCodigoIn(listaCodigosAntigos)).thenReturn(listaCartas);
        when(repository.saveAll(listaCartasAtualizadas)).thenReturn(listaCartasAtualizadas);

        List<Carta> cartasRetorno = cartaService.atualizarCodigosCartas();

        assertNotNull(cartasRetorno);
        assertEquals(cartasRetorno.get(0).getCodigo(), changeLogResponse.getData().get(0).getNewId());
    }

    @Test
    void naoDeveAtualizarSeDataChangeLogForMenorQueData() {

    }

    private Carta criarCarta(String codigo, String nome) {
        Carta carta = new Carta();

        carta.setCodigo(codigo);
        carta.setNome(nome);

        return carta;
    }

    private ChangeLogResponse criarChangeLogResponse(String codigoNovo, String codigoAntigo, LocalDateTime dataHora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DadosChangeLogResponse dadosChangeLogResponse = DadosChangeLogResponse
                .builder()
                .date(dataHora.format(formatter))
                .nome("Carta Teste")
                .newId(codigoNovo)
                .oldId(codigoAntigo)
                .build();

        List<DadosChangeLogResponse> listaDadosChangeLogResponse = Arrays.asList(dadosChangeLogResponse);

        return ChangeLogResponse.builder().data(listaDadosChangeLogResponse).build();
    }

}
