package dev.trier.ecommerce.dto.produto.Criacao;

import dev.trier.ecommerce.model.enums.CategoriaProduto;
import org.springframework.web.multipart.MultipartFile;

public record ProdutoCriarComEstoqueDto(
        String nmProduto,

        Double vlProduto,

        CategoriaProduto dsProduto,

        MultipartFile imgProduto,

        String flAtivo,

        Integer cdEmpresa,

        Integer qtdEstoqueProduto
) {
}
