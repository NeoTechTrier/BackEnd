package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.produto.criacao.CriarProdutoResponseDto;
import dev.trier.ecommerce.dto.produto.response.*;
import dev.trier.ecommerce.dto.produto.criacao.ProdutoCriarDto;
import dev.trier.ecommerce.exceptions.RecursoNaoEncontradoException;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;

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
    @Operation(summary = "Criar produto", description = "Cria um novo produto com imagem (multipart/form-data)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CriarProdutoResponseDto> criarProduto(@ModelAttribute @Valid ProdutoCriarDto produtoCriarDto) {  //ModelAttribute para receber multipart
        CriarProdutoResponseDto produtoCriado = produtoService.criarProduto(produtoCriarDto);
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

    //Precisa trocar o return para ResponseDto
    @CrossOrigin
    @GetMapping(path = "/listar/todos")
    @Operation(summary = "Listar produtos", description = "Lista todos os produtos")

    public ResponseEntity<List<ListarProdutosResponseDto>> listarTodos() {
        //var lista = produtoService.listarProdutos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoService.listarProdutos());
    }

    @GetMapping(path = "/categoria/{categoria}")
    @Operation(summary = "Listar por categoria", description = "Lista produtos filtrando pela categoria")
    public ResponseEntity<List<ProdutoIdResponseDto>> listarPorCategoria(@PathVariable String categoria) {
        List<ProdutoIdResponseDto> lista = produtoService.listarProdutosPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    //Verficar lógica desse metodo get

    @CrossOrigin
    @GetMapping(path = "/{cdProduto}/imagem")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter imagem do produto", description = "Retorna a imagem do produto em formato JPEG")
    public ResponseEntity<byte[]> listarImagem(@PathVariable Integer cdProduto) {
        ProdutoModel produto = produtoService.buscarProdutoPorId(cdProduto);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(produto.getImgProduto());
    }


/*
//Endpoint em teste de criação para usar somente DTO
    @CrossOrigin
    @GetMapping(path = "/{cdProduto}/imagem2")
    @Transactional
    public ResponseEntity<Optional<ListarImagemCdProdutoDto>> listarImagemCdProduto(Integer cdProduto) {
        Optional<ListarImagemCdProdutoDto> imagem = produtoService.lsitarImagemCdProduto(cdProduto);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagem);
    }

 */








    //Endpoint para buscar dados do produto, uso de dto para definidas as entidades em get do BD
    @CrossOrigin
    @GetMapping(path = "/{cdProduto}/idProduto")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os dados do produto pelo código")
    public ResponseEntity<Optional<ProdutoIdResponseDto>> buscarProdutoId(@PathVariable Integer cdProduto) {
        //ProdutoIdResponseDto response = produtoService.buscarProdutoId(cdProduto);
        return ResponseEntity.ok()
                .body(produtoService.buscarProdutoId(cdProduto));
    }

    @CrossOrigin
    @GetMapping(path = "/{nmProduto}")
    @Operation(summary = "Buscar produto por nome", description = "Retorna os dados do produto pelo nome")
    public ResponseEntity<Optional<ProdutoNomeResponseDto>> listarProdutoNome(@PathVariable String nmProduto) {
        Optional<ProdutoNomeResponseDto> response = produtoService.listarProdutoNome(nmProduto);
        return ResponseEntity.ok()
                .body(response);
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/update/texto/{cdProduto}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar texto do produto", description = "Atualiza os dados textuais de um produto")
    public ResponseEntity<ProdutoTextUpdateResponseDto> atualizarProdutoTexto(
            @PathVariable Integer cdProduto,
            @RequestBody @Valid ProdutoTextUpdateDto updateRequestDto
    ) {
        try {
            ProdutoTextUpdateResponseDto produtoDto = produtoService.atualizarProdutoTexto(updateRequestDto, cdProduto);
            return ResponseEntity.ok(produtoDto);
        } catch (RecursoNaoEncontradoException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/update/imagem/{cdProduto}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualizar imagem do produto", description = "Atualiza a imagem de um produto")
    public ResponseEntity<Void> atualizarImagemProduto(
            @PathVariable Integer cdProduto,
            @RequestPart("imgProduto") MultipartFile imgProduto
    ) {
        try {
            produtoService.atualizarImagemProduto(cdProduto, imgProduto);
            return ResponseEntity.ok().build();
        } catch (RecursoNaoEncontradoException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // --- Novo endpoint: deletar produto (somente ADMIN) ---
    @DeleteMapping("/delete/{cdProduto}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer cdProduto) {
        try {
            produtoService.removerProduto(cdProduto);
            return ResponseEntity.noContent().build();
        } catch (dev.trier.ecommerce.exceptions.EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
