package dev.trier.ecommerce.dto.usuario.criacao;

public record UsuarioResponseDto(
        String nmCliente,
        String dsEmail,
        String nuTelefone,
        String dsCidade
) {
}
