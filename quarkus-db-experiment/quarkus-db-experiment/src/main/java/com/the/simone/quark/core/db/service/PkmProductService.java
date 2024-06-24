package com.the.simone.quark.core.db.service;

import com.the.simone.quark.core.db.model.entity.PokemonProduct;
import com.the.simone.quark.core.db.model.request.PkmProductRequest;
import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@ApplicationScoped
@Slf4j
@RunOnVirtualThread // questa annotation serve per dirgli di girare su thread virtuale
public class PkmProductService {


    public Uni<PokemonProduct> saveProduct(PkmProductRequest request){

        log.info("SaveProduct service started with raw request: {}",request);

        //checko se gia esiste un entity con stesso nome
        var pkm = PokemonProduct.findAllByName(request.nome());


        //TODO: inserire check su pkm , non ho capito come cazzo si fa al momento


        var entity = new PokemonProduct();
        entity.setDataRilascio(request.dataRilascio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        entity.setNome(request.nome());
        entity.setPrezzoListino(request.prezzoListino());
        entity.setUpdatePrezzo(new ArrayList<>());

        // sta parte e da testare, non e chiarissima
        entity.persistAndFlush()
                .onFailure(Exception.class)
                .invoke(err -> {
                    throw new RuntimeException(err.getMessage());
                });

        log.info("SaveProduct service ended successfully");
        //TODO: rivedere che cazzo si deve fare, sto ritornando entity montata, non response
        return Uni.createFrom().item(entity);
    }
}
