package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.produto.Criacao.CriarProdutoRequestDto;
import dev.trier.ecommerce.dto.produto.Criacao.CriarProdutoResponseDto;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Produto de criação e modificação do produto")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("/buscar")
    public List<ProdutoModel> buscarProdutos() {
        return produtoService.buscarProdutos();
    }

    //ID Cliente;
    /*
    @GetMapping("/{id}")
    public ProdutoModel buscarProduto(@PathVariable Integer id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

     */
    //Buscar pedido Cliente, é Produto ou Pedido?

    @PostMapping("/cadastrar")
    public ResponseEntity<CriarProdutoResponseDto>  cadastrarProduto(@RequestBody CriarProdutoRequestDto criarProdutoRequestDto) {
        CriarProdutoResponseDto criar = produtoService.cadastrarProduto(criarProdutoRequestDto);
        return ResponseEntity.status(201).body(criar);
    }

}
