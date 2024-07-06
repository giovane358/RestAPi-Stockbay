package br.com.stockbay.userstockbay.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record ProductRequest(

        String id,
        @NotBlank String name,
        @NotNull Integer price_in_cents,
         Date dtCompra) {
}
