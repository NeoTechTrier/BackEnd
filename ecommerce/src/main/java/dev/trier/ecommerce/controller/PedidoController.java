package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Capacidade de criação e modificação do pedido")
public class PedidoController {

    public final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //Busca todos os pedidos
    public List<PedidoModel> buscarPedidos(){
        return pedidoService.buscarPedidos();
    }




}
