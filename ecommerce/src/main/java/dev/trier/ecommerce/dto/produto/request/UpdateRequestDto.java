package dev.trier.ecommerce.dto.produto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public record UpdateRequestDto(
        @Schema(description = "Nome do produto.", example = "Placa de vídeo RTX 4090")
        String nmProduto,

        @Schema(description = "Valor do produto em reais (precisa ser positivo).", example = "5999.90")
        Double vlProduto,

        @Schema(description = "Categoria do produto. Escritas válidas: PERIFERICOS, PROCESSADOR, RAM, GABINETE.", example = "RAM")
        String dsCategoria,

        @Schema(description = "Descrição do produto", example = "Memória Ram 32GB RedDragon")
        String dsProduto,

        @Schema(description = "Código identificador da empresa relacionada ao produto.", example = "1")
        Integer cdEmpresa,

        @Schema(description = "Imagem do produto que vai ser utilizado.")
        MultipartFile imgProduto
) {
}
