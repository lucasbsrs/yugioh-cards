package com.lucas.yugiohcards.adapter.out.postgressql.entities;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Entity
public class PrecoCarta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String cardmarket;

    private String tcgplayer;

    private String ebay;

    private String amazon;

    private String coolstuffinc;

    @Column(name = "carta_id")
    private Long cartaId;

}
