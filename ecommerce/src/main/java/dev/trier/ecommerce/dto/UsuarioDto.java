package dev.trier.ecommerce.dto;

public record UsuarioDto(
        String nmCliente,
        String nuCPF,
        Integer nuTelefone,
        String nuRG,
        String dsEndereco,
        String nuEndereco,
        String flAtivo
) {
}
