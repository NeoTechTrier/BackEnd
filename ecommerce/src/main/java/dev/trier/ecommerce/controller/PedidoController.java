package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.pedido.criacao.PedidoCriarDto;
import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Capacidade de criação e modificação do pedido")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(path = "/criar")
    public ResponseEntity<PedidoModel> criarPedido(@RequestBody @Valid PedidoCriarDto pedidoCriarDto) {
        PedidoModel pedidoModel = pedidoService.criarPedido(pedidoCriarDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoModel);
    }
    @GetMapping(path = "/listar/todos")
    public ResponseEntity<List<PedidoModel>> listarPedidos(){
        var lista = pedidoService.listarPedidos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
}
