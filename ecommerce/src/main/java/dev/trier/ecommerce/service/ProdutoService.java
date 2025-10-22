package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.produto.request.UpdateRequestDto;
import dev.trier.ecommerce.dto.produto.response.ProdutoIdResponseDto;
import dev.trier.ecommerce.dto.produto.response.UpdateResponseDto;
import dev.trier.ecommerce.dto.produto.criacao.ProdutoCriarDto;
import dev.trier.ecommerce.exceptions.RecursoNaoEncontradoException;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.model.enums.CategoriaProduto;
import dev.trier.ecommerce.repository.EmpresaRepository;
import dev.trier.ecommerce.repository.ProdutoRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProdutoService {


    private final ProdutoRespository produtoRespository;
    private final EmpresaRepository empresaRepository;

    @Transactional
    public ProdutoModel criarProduto(ProdutoCriarDto produtoCriarDto) {
        EmpresaModel empresaModel = empresaRepository.findById(produtoCriarDto.cdEmpresa())
                .orElseThrow(
                        () -> new RuntimeException("Empresa não encontrada para o código: " + produtoCriarDto.cdEmpresa()) // Procura empresa antes de criar o produto
                );
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNmProduto(produtoCriarDto.nmProduto());
        produtoModel.setVlProduto(produtoCriarDto.vlProduto());
        produtoModel.setDsCategoria(CategoriaProduto.valueOf(produtoCriarDto.dsCategoria()));
        produtoModel.setDsProduto(produtoCriarDto.dsProduto());
        produtoModel.setEmpresa(empresaModel);


        MultipartFile imgProduto = produtoCriarDto.imgProduto();
        if (imgProduto != null && !imgProduto.isEmpty()) {
            try {
                produtoModel.setImgProduto(imgProduto.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar imagem do produto", e);
            }
        }

        return produtoRespository.save(produtoModel);
    }

    public List<ProdutoModel> listarProdutos(){
      return produtoRespository.findAll();
    }

    public List<ProdutoIdResponseDto> listarProdutosPorCategoria(String categoria) {
        CategoriaProduto cat = CategoriaProduto.fromString(categoria);
        return produtoRespository.findAllByDsCategoria(cat).stream()
                .map(produto -> new ProdutoIdResponseDto(
                        produto.nmProduto(),
                        produto.vlProduto(),
                        produto.dsProduto(),
                        produto.categoria()
                ))
                .toList();
    }

    public ProdutoModel buscarProdutoPorId(Integer cdProduto) {
        return produtoRespository.findByCdProduto(cdProduto)
                .orElseThrow(
                        () -> new RuntimeException("Produto não encontrado"));
    }


    public Optional<ProdutoIdResponseDto> buscarProdutoId(Integer cdProduto) {
        return produtoRespository.findByCdProduto(cdProduto)
                .map(produto -> new ProdutoIdResponseDto(
                        produto.getNmProduto(),
                        produto.getVlProduto(),
                        produto.getDsProduto(),
                        produto.getDsCategoria()
                        ));

                //.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    }

    public UpdateResponseDto atualizarProduto(UpdateRequestDto response, Integer cdProduto) {
        ProdutoModel produtoModel = produtoRespository.findByCdProduto(cdProduto)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Produto não encontrado" + response.cdProduto()));
        produtoModel.setNmProduto(response.nmProduto());
        produtoModel.setVlProduto(response.vlProduto());
        produtoModel.setDsProduto(response.dsProduto());
        ProdutoModel salvo = produtoRespository.save(produtoModel);
        //if (response.imgProduto() != null && !response.imgProduto().isEmpty()) {}

        return new UpdateResponseDto(
                salvo.getNmProduto(),
                salvo.getVlProduto(),
                salvo.getDsProduto()
        );
    }

}
