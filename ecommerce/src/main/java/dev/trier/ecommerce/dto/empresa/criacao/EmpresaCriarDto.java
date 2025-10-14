package dev.trier.ecommerce.dto.empresa.criacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para criação de uma nova empresa")
public record EmpresaCriarDto(
    @NotBlank
    @Schema(description = "Nome fantasia da empresa", example = "Neo Tech Ltda", requiredMode = Schema.RequiredMode.REQUIRED)
    String nmFantasia,

    @NotBlank
    @Schema(description = "Razão social da empresa", example = "Neo Tech Tecnologia Ltda", requiredMode = Schema.RequiredMode.REQUIRED)
    String nmRazao,

    @NotBlank
    @Size(min = 18, max = 18)
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")
    @Schema(description = "CNPJ da empresa nesse formato: 00.000.000/0000-00)", example = "12.345.678/0001-99", requiredMode = Schema.RequiredMode.REQUIRED)
    String nuCNPJ,

    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\d{10,15}")
    @Schema(description = "Telefone da empresa (apenas números, com DDD)", example = "11987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    String nuTelefone,

    @NotBlank
    @Schema(description = "Descrição do endereço da empresa", example = "Rua estrada Geral, Bairro Travessão", requiredMode = Schema.RequiredMode.REQUIRED)
    String dsEndereco,

    @NotBlank
    @Schema(description = "Número do endereço", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    String nuEndereco
) {}
