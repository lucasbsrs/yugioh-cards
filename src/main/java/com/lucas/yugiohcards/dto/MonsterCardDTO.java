package com.lucas.yugiohcards.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class MonsterCardDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String number;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String description;

    @NotNull(message = "Preenchimento obrigatório")
    private Long status;

    @NotNull(message = "Preenchimento obrigatório")
    private Long attribute;

    @NotNull(message = "Preenchimento obrigatório")
    private Long level;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String type;

    private boolean effect;

    @NotNull(message = "Preenchimento obrigatório")
    private Double attack;

    @NotNull(message = "Preenchimento obrigatório")
    private Double defense;

}
