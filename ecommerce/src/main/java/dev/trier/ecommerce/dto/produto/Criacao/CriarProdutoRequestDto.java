package dev.trier.ecommerce.dto.produto.Criacao;

import dev.trier.ecommerce.model.EmpresaModel;

public record CriarProdutoRequestDto(
       String nmProduto,
       String vlProduto,
       String dsProduto,
       EmpresaModel empresaModel,
       String flAtivo
) {
}
