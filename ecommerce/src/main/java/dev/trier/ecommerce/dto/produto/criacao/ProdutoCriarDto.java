package dev.trier.ecommerce.dto.produto.criacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
@Schema(description = "DTO para criação de um novo produto.")
public record ProdutoCriarDto(

        @Schema(description = "Nome do produto.", example = "Placa de vídeo RTX 4090", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Obrigatório nome no produto")
        @Size(min = 1, message = "O produto precisa ter mais de um caracter em seu nome")
        String nmProduto,

        @Schema(description = "Valor do produto em reais (precisa ser positivo).", example = "5999.90", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Obrigatório colocar valor no produto")
        @Positive(message = "Valor do produto precisa ser positivo")
        Double vlProduto,

        @NotBlank(message = "Obrigatório preenchimento da categoria")
        @Pattern(regexp = "^(PLACA_DE_VIDEO|PROCESSADOR|RAM|HEADSET|TECLADO|MOUSE|MONITOR)$", message = "Equipamento inválido. As opções válidas são: PLACA_DE_VIDEO, PROCESSADOR, RAM, HEADSET, TECLADO, MOUSE, MONITOR.")
        String dsCategoria,

        @Schema(description = "Categoria do produto. Escritas válidas: PLACA_DE_VIDEO, PROCESSADOR, RAM, HEADSET, TECLADO, MOUSE, MONITOR.", example = "PLACA_DE_VIDEO", requiredMode = Schema.RequiredMode.REQUIRED)
        String dsProduto,



        MultipartFile imgProduto,


        @Schema(description = "Código identificador da empresa relacionada ao produto.", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer cdEmpresa
) {}
