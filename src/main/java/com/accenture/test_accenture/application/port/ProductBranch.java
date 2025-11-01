package com.accenture.test_accenture.application.port;

public record ProductBranch(
        Long productoId,
        String productoNombre,
        Integer stock,
        Long sucursalId,
        String sucursalNombre
) {
}
