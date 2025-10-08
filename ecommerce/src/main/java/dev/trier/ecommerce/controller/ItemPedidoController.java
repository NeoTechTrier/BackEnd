package dev.trier.ecommerce.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itempedido")
@Tag(name = "Item Pedido", description = "Capacidade de criação e modificação do item pedido")
public class ItemPedidoController {
}
