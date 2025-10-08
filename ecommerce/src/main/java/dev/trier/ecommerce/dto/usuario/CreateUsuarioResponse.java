package dev.trier.ecommerce.dto.usuario;

public record CreateUsuarioResponse(
        Integer cdUsuario,
        String nmUsuario,
        String flAtivo
) {
}
