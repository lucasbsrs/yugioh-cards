package com.lucas.yugiohcards.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carta_id")
    private Carta carta;

}
