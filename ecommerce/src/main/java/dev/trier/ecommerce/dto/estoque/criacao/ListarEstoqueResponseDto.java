package dev.trier.ecommerce.dto.estoque.criacao;

public record ListarEstoqueResponseDto(
        Integer cdEstoque,
        Integer qtdEstoqueProduto,
        String flAtivo,
        Integer cdProduto,
        String nmProduto
) {
}
