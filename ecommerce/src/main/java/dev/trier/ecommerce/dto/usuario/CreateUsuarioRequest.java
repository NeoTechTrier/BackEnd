package dev.trier.ecommerce.dto.usuario;

public record CreateUsuarioRequest(
        String nmCliente,
        String nuCPF,
        Integer nuTelefone,
        String nuRG,
        String dsEndereco,
        String nuEndereco,
        String flAtivo
) {
}
