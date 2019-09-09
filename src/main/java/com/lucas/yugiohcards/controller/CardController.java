package com.lucas.yugiohcards.controller;

import com.lucas.yugiohcards.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = "*")
public class CardController {

    private Map<Integer, Card> cards;

    public CardController() {
        cards = new HashMap<Integer, Card>();

        Card c1 = new Card("Name teste", "Wind", "Testeeee", 2, "332423asda", "type teste", 13300, 1344);
        Card c2 = new Card("Name teste", "Wind", "Testeeee", 2, "332423asda", "type teste", 13300, 1344);
        Card c3 = new Card("Name teste", "Wind", "Testeeee", 2, "332423asda", "type teste", 13300, 1344);

        cards.put(1, c1);
        cards.put(2, c2);
        cards.put(3, c3);
    }

    @GetMapping
    public ResponseEntity<List<Card>> listCards(){
        return new ResponseEntity<List<Card>>(new ArrayList<Card>(cards.values()), HttpStatus.OK);
    }

}
