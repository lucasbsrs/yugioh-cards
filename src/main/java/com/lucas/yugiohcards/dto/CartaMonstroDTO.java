package com.lucas.yugiohcards.dto;

import com.lucas.yugiohcards.enums.AtributoEnum;
import com.lucas.yugiohcards.enums.StatusCartaEnum;
import com.lucas.yugiohcards.model.TipoCarta;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class CartaMonstroDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String descricao;

    @NotNull(message = "Preenchimento obrigatório")
    private StatusCartaEnum status;

    @NotNull(message = "Preenchimento obrigatório")
    private AtributoEnum atributo;

    @NotNull(message = "Preenchimento obrigatório")
    private Long level;

    @NotEmpty(message = "Preenchimento obrigatório")
    private List<TipoCarta> tipoCartas;

    private boolean efeito;

    @NotNull(message = "Preenchimento obrigatório")
    private Double ataque;

    @NotNull(message = "Preenchimento obrigatório")
    private Double defesa;

}
