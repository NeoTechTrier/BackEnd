package dev.trier.ecommerce.dto.produto.response;

//Atualizar dados do produto
public record UpdateResponseDto(
        String nmProduto,
        Double vlProduto,
        String dsProduto
) {
}
