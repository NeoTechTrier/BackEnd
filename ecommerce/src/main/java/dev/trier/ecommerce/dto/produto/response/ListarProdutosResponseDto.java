package dev.trier.ecommerce.dto.produto.response;



public record ListarProdutosResponseDto(
        String nmProduto,
        Double vlProduto,
        String dsCategoria,
        String dsProduto,
        byte[] imgProduto,
        Integer cdEmpresa
        //Não pode contre camadas do Model, precisa modificar
) {
}
