package com.the.simone.quark.core.db.model.entity;

import com.the.simone.quark.core.db.model.internal.PriceUpdate;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class PokemonProduct extends PanacheEntity {

    // voglio tenere traccia dei prodotti generici (non correlati a l'acquisto effettivo, e tenere traccia del prezzo nel tempo)
    @Column(unique = true)
    private String nome;
    private Double prezzoListino;
    private String dataRilascio;
    // faccio un append alla lista ogni volta che aggiorno il prezzo nel tempo
    private List<PriceUpdate> updatePrezzo;

    //metodo ricerca per nome, le queery le fai come metodo qui se usi il panache entity, qui stiamo a usa il ractive
    public static Uni<PokemonProduct> findAllByName(String nome){
        return find("nome",nome).firstResult();
    }
    // per fare una queery con piu paramas si deve fa una mappa e poi queery a cui passi mappa come sotto
    public static Uni<List<PokemonProduct>> findAllByPrice(Double start,Double end){
        var map = new HashMap<>();
        map.put("startPrice",start);
        map.put("endPrice",end);
        return find("prezzoListino >= :startPrice and prezzoListino <= :endPirce",map).list();
    }



}
