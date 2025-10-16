package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.produto.criacao.ProdutoCriarDto;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Produto de criação e modificação do produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(path = "/criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoModel> criarProduto(@ModelAttribute @Valid ProdutoCriarDto produtoCriarDto) {  //ModelAttribute para receber multipart
        ProdutoModel produtoCriado = produtoService.criarProduto(produtoCriarDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoCriado);
    }

    @GetMapping(path = "/listar/todos")
    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        var lista = produtoService.listarProdutos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    @GetMapping("/{cdProduto}/imagem")
    @Transactional
    public ResponseEntity<byte[]> listarImagem(@PathVariable Integer cdProduto) {
        ProdutoModel produto = produtoService.buscarProdutoPorId(cdProduto);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(produto.getImgProduto());
    }
}
