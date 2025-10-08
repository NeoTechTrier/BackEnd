package dev.trier.ecommerce.dto.usuario;

import dev.trier.ecommerce.model.enums.UsersRole;

public record CriarUsuarioRequestDto(
        UsersRole usersRole,
        String nmCliente,
        String nuCPF,
        Integer nuTelefone,
        String nuRG,
        String dsEndereco,
        String nuEndereco,
        String flAtivo
) {
}
