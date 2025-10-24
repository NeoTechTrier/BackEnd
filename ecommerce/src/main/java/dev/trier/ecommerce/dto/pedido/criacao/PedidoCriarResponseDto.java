package dev.trier.ecommerce.dto.pedido.criacao;

import dev.trier.ecommerce.model.enums.FormaPagamento;

public record PedidoCriarResponseDto(
        FormaPagamento formaPagamento,
        Double vlFrete,
        Double vlTotalPedido
) {
}
