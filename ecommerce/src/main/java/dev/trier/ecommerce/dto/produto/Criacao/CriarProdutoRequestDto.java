package dev.trier.ecommerce.dto.produto.Criacao;

import dev.trier.ecommerce.model.EmpresaModel;

public record CriarProdutoRequestDto(
       String nmProduto,
       //String dsProduto,
       Double vlProduto,
       EmpresaModel empresaModel,
       String flAtivo
) {
};
