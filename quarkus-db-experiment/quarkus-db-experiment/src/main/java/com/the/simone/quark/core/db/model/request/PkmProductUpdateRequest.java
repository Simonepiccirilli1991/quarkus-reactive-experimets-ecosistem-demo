package com.the.simone.quark.core.db.model.request;

import com.the.simone.quark.core.db.model.internal.PriceUpdate;

public record PkmProductUpdateRequest(String nome, PriceUpdate update) {
}
