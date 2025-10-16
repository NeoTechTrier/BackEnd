package dev.trier.ecommerce.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itempedido")
@Tag(name = "Item Pedido", description = "Capacidade de criação e modificação do item pedido")
public class ItemPedidoController {

    //Metodo dedicado a salvar Todos os itens. Mas esses itens estão atralados ao pedido do cliente?
    //Pq para salvar os itens, preciso de um usuario atual comprando =.

    //Está classe está atralado a class Pedido, pois é dela que permite

    //CalcularDescontoFinal

    //Buscar Por Id

    //Buscar Todos

    //Salvar (Talvez possa salvar no carrinhou ou direto no carrinho )
}
