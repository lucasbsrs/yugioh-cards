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
public class ImagemCarta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long codigo;

    private String url;

    private String urlImagemPequena;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "carta_id")
//    private Carta carta;

}
