package com.the.simone.quark.core.db.model.request;

import java.time.LocalDateTime;

public record PkmProductRequest(String nome, Double prezzoListino, LocalDateTime dataRilascio) {
}
