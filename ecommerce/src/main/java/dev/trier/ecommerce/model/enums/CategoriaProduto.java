package dev.trier.ecommerce.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoriaProduto {
    PLACA_DE_VIDEO,
    PROCESSADOR,
    RAM,
    HEADSET,
    TECLADO,
    MOUSE,
    MONITOR;

    @JsonCreator
    public static CategoriaProduto fromString(String value) {
        return CategoriaProduto.valueOf(value.toUpperCase().replace(" ", "_"));
        // Isso permite a pessoa digitar placa de video, sem _, bem simples e funcional
    }
}
