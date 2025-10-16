package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriarDto;
import dev.trier.ecommerce.model.EstoqueModel;
import dev.trier.ecommerce.repository.EstoqueRepository;
import dev.trier.ecommerce.service.EstoqueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque", description = "Capacidade de criação e modificação do estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping(path = "/criar")
    public ResponseEntity<EstoqueModel> criarEstoque(@RequestBody @Valid EstoqueCriarDto estoqueCriarDto) {
        EstoqueModel estoqueCriado = estoqueService.criarEstoque(estoqueCriarDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estoqueCriado);
    }

    @GetMapping(path = "/listar/todos")
    public ResponseEntity<List<EstoqueModel>> listarEstoque() {
        var lista = estoqueService.listarEstoque();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
}
