package dev.trier.ecommerce.dto.produto.Criacao;

import dev.trier.ecommerce.model.enums.CategoriaProduto;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public record ProdutoCriarDto(


        @NotBlank(message = "Obrigatório nome no produto")
        @Size(min = 1, message = "O produto precisa ter mais de um caracter em seu nome")
        String nmProduto,

        @NotNull(message = "Obrigatório colocar valor no produto")
        @Positive(message = "Valor do produto precisa ser positivo")
        Double vlProduto,

        @NotBlank(message = "Obrigatório preenchimento da categoria")
        @Pattern(regexp = "^(PLACA_DE_VIDEO|PROCESSADOR|RAM|HEADSET|TECLADO|MOUSE|MONITOR)$",
                message = "Equipamento inválido. As opções válidas são: PLACA_DE_VIDEO, PROCESSADOR, RAM, HEADSET, TECLADO, MOUSE, MONITOR.")
        CategoriaProduto dsProduto,


        MultipartFile imgProduto,

        String flAtivo,

        Integer cdEmpresa

) {
}
