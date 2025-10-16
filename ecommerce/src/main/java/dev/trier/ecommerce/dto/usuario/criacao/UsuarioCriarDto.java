package dev.trier.ecommerce.dto.usuario.criacao;

import dev.trier.ecommerce.model.enums.UsersRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "DTO para criação de usuário")
public record UsuarioCriarDto(
    @NotNull(message = "O papel do usuário é obrigatório.")
    @Schema(description = "Categoria do usuário", example = "User", requiredMode = Schema.RequiredMode.REQUIRED)
    UsersRole userRole,

    @NotBlank(message = "O nome do cliente é obrigatório.")
    @Schema(description = "Nome do cliente", example = "Davi Beckhauser", requiredMode = Schema.RequiredMode.REQUIRED)
    String nmCliente,

    @NotBlank(message = "O Email é obrigatório.")
    @Schema(description = "Email para login do usuário", example = "112.123.123-22", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsEmail,

    @NotBlank(message = "O Email é obrigatório.")
    @Schema(description = "Email para login do usuário", example = "112.123.123-22", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\n")
    String dsSenha,

    @NotBlank(message = "O CPF é obrigatório.")
    @Schema(description = "Número de CPF", example = "112.123.123-22", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp ="^(\\d{3}\\.?){3}\\-?\\d{2}$\n")
    String nuCPF,

    @NotNull(message = "O telefone é obrigatório.")
    @Schema(description = "Número de telefone da pessoa", example = "48920023805", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer nuTelefone,

    @Schema(description = "Número de RG da pessoa", example = "7510437")
    String nuRG,

    @NotBlank(message = "A cidade é obrigatório.")
    @Schema(description = "Nome da cidade", example = "Braço do Norte", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsCidade,

    @NotBlank(message = "O estado é obrigatório.")
    @Schema(description = "Nome do estado", example = "Santa Catarina", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsEstado,

    @NotBlank(message = "O endereço é obrigatório.")
    @Schema(description = "Endereço de localização de sua moradia", example = "Travessão, estrada geral", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsEndereco,

    @NotBlank(message = "O número do endereço é obrigatório.")
    @Schema(description = "Número residencial da pessoa", example = "102 ou SN", requiredMode = Schema.RequiredMode.REQUIRED)
    String nuEndereco
) {}

