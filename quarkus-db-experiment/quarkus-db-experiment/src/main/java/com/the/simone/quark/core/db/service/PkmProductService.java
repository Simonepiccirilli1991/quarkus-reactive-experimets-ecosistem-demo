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

        var resp = entity.persist();

        log.info("SaveProduct service ended successfully");
        //TODO: rivedere che cazzo si deve fare
        return resp.flatMap(i -> i);
    }
}
