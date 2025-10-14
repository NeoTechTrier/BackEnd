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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Produto de criação e modificação do produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(headers = "/criar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoModel> criarProduto(@ModelAttribute @Valid ProdutoCriarDto dto) {  //ModelAttribute para receber multipart
        ProdutoModel produtoCriado = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoCriado);
    }
}
