package com.lucas.yugiohcards.service;

import com.lucas.yugiohcards.integrations.client.YgoProClient;
import com.lucas.yugiohcards.integrations.response.ChangeLogIdResponse;
import com.lucas.yugiohcards.integrations.response.DadosChangeLogIdResponse;
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
    private DataBaseService dataBaseService;

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

        ChangeLogIdResponse changeLogIdResponse = criarChangeLogResponse(codigoNovo, codigoAntigo, dataAtualMaisUm);

        List<String> listaCodigosAntigos = Arrays.asList(codigoAntigo);
        List<Carta> listaCartas = Arrays.asList(carta);
        List<Carta> listaCartasAtualizadas = Arrays.asList(cartaAtualizada);

        when(ygoProClient.consultarChangeLogId()).thenReturn(changeLogIdResponse);
        when(repository.findByCodigoIn(listaCodigosAntigos)).thenReturn(listaCartas);
        when(repository.saveAll(listaCartasAtualizadas)).thenReturn(listaCartasAtualizadas);

        List<Carta> cartasRetorno = dataBaseService.atualizarCodigosCartas();

        assertNotNull(cartasRetorno);
        assertEquals(changeLogIdResponse.getData().get(0).getNewId(), cartasRetorno.get(0).getCodigo());
    }

    @Test
    @DisplayName("Não deve atualizar nenhum código caso data atual seja maior que a data do ChangeLogID")
    void naoDeveAtualizarCodigoCarta() {

        String codigoAntigo = "1111";
        String codigoNovo = "2222";
        LocalDateTime dataAtualMaisUm = LocalDateTime.now().minusDays(1);

        Carta carta = criarCarta(codigoAntigo, "Carta Teste");
        Carta cartaAtualizada = criarCarta(codigoNovo, "Carta Teste");

        ChangeLogIdResponse changeLogIdResponse = criarChangeLogResponse(codigoNovo, codigoAntigo, dataAtualMaisUm);

        List<String> listaCodigosAntigos = Arrays.asList(codigoAntigo);
        List<Carta> listaCartas = Arrays.asList(carta);
        List<Carta> listaCartasAtualizadas = Arrays.asList(cartaAtualizada);

        when(ygoProClient.consultarChangeLogId()).thenReturn(changeLogIdResponse);
        when(repository.findByCodigoIn(listaCodigosAntigos)).thenReturn(listaCartas);
        when(repository.saveAll(listaCartasAtualizadas)).thenReturn(listaCartasAtualizadas);

        List<Carta> cartasRetorno = dataBaseService.atualizarCodigosCartas();

        assertEquals(0, cartasRetorno.size());
    }

    private Carta criarCarta(String codigo, String nome) {
        Carta carta = new Carta();

        carta.setCodigo(codigo);
        carta.setNome(nome);

        return carta;
    }

    private ChangeLogIdResponse criarChangeLogResponse(String codigoNovo, String codigoAntigo, LocalDateTime dataHora){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DadosChangeLogIdResponse dadosChangeLogIdResponse = DadosChangeLogIdResponse
                .builder()
                .date(dataHora.format(formatter))
                .nome("Carta Teste")
                .newId(codigoNovo)
                .oldId(codigoAntigo)
                .build();

        List<DadosChangeLogIdResponse> listaDadosChangeLogIdResponse = Arrays.asList(dadosChangeLogIdResponse);

        return ChangeLogIdResponse.builder().data(listaDadosChangeLogIdResponse).build();
    }

}
