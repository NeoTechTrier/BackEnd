package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.produto.criacao.CriarProdutoResponseDto;
import dev.trier.ecommerce.dto.produto.request.UpdateRequestDto;
import dev.trier.ecommerce.dto.produto.response.ProdutoIdResponseDto;
import dev.trier.ecommerce.dto.produto.criacao.ProdutoCriarDto;
import dev.trier.ecommerce.dto.produto.response.ProdutoNomeResponseDto;
import dev.trier.ecommerce.dto.produto.response.UpdateResponseDto;
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
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Produto de criação e modificação do produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(path = "/criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProdutoModel> criarProduto(@ModelAttribute @Valid ProdutoCriarDto produtoCriarDto) {  //ModelAttribute para receber multipart
        ProdutoModel produtoCriado = produtoService.criarProduto(produtoCriarDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoCriado);
    }

/*
//EM TESTE EXEMPLO
    @PostMapping(path = "/criar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CriarProdutoResponseDto> criarProduto(@ModelAttribute @Valid ProdutoCriarDto produtoCriarDto) {  //ModelAttribute para receber multipart
        CriarProdutoResponseDto CriarProdutoResponseDto = produtoService.criarProduto(produtoCriarDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CriarProdutoResponseDto);
    }

 */

    @CrossOrigin
    @GetMapping(path = "/listar/todos")
    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        var lista = produtoService.listarProdutos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    @GetMapping(path = "/categoria/{categoria}")
    public ResponseEntity<List<ProdutoIdResponseDto>> listarPorCategoria(@PathVariable String categoria) {
        List<ProdutoIdResponseDto> lista = produtoService.listarProdutosPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    //Verficar lógica desse metodo get

    @CrossOrigin
    @GetMapping(path = "/{cdProduto}/imagem")
    @Transactional
    public ResponseEntity<byte[]> listarImagem(@PathVariable Integer cdProduto) {
        ProdutoModel produto = produtoService.buscarProdutoPorId(cdProduto);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(produto.getImgProduto());
    }

    //Endpoint para buscar dados do produto, uso de dto para definidas as entidades em get do BD


    @CrossOrigin
    @GetMapping(path = "/{cdProduto}")
    public ResponseEntity<Optional<ProdutoIdResponseDto>> buscarProdutoId(@PathVariable Integer cdProduto) {
        Optional<ProdutoIdResponseDto> response = produtoService.buscarProdutoId(cdProduto);
        return ResponseEntity.ok()
                .body(response);
    }

    @CrossOrigin
    @GetMapping("/{nmProduto}")
    public ResponseEntity<Optional<ProdutoNomeResponseDto>> listarProdutoNome(@PathVariable String nmProduto) {
        Optional<ProdutoNomeResponseDto> response = produtoService.listarProdutoNome(nmProduto);
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/update/{cdProduto}")
    public ResponseEntity<UpdateResponseDto> atualizarProduto(@PathVariable Integer cdProduto, @Valid @RequestBody  UpdateRequestDto updateRequestDto ) {
        try {
            UpdateResponseDto produtoDto = produtoService.atualizarProduto(updateRequestDto,cdProduto);
            return ResponseEntity.ok().body(produtoDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
