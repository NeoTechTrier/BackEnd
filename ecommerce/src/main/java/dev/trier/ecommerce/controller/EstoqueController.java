package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriadoRespostaDto;
import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriarDto;
import dev.trier.ecommerce.dto.estoque.modificacao.EstoqueUpdateDto;
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
@CrossOrigin("*")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping(path = "/criar")
    public ResponseEntity<EstoqueCriadoRespostaDto> criarEstoque(@RequestBody @Valid EstoqueCriarDto estoqueCriarDto) {
        EstoqueCriadoRespostaDto estoqueCriado = estoqueService.criarEstoque(estoqueCriarDto);
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

    @PutMapping("/update/{cdEstoque}")
    public ResponseEntity<EstoqueCriadoRespostaDto> atualizarEstoque(@PathVariable Integer cdEstoque,
                                                                    @RequestBody @Valid EstoqueUpdateDto updateDto) {
        try {
            EstoqueCriadoRespostaDto atualizado = estoqueService.atualizarEstoque(cdEstoque, updateDto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
