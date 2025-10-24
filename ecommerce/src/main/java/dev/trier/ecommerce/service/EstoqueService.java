package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriadaRespostaDto;
import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriadoRespostaDto;
import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriarDto;
import dev.trier.ecommerce.dto.estoque.criacao.ListarEstoqueResponseDto;
import dev.trier.ecommerce.model.EstoqueModel;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.repository.EstoqueRepository;
import dev.trier.ecommerce.repository.ProdutoRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRespository produtoRespository;

    @Transactional
    public EstoqueCriadoRespostaDto criarEstoque(EstoqueCriarDto estoqueCriarDto) {

        ProdutoModel produtoModel = produtoRespository.findById(estoqueCriarDto.cdProduto())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para o código: " + estoqueCriarDto.cdProduto()));  //Procura cdProduto antes de criar no estoque

        EstoqueModel estoqueModel = new EstoqueModel();
        estoqueModel.setProduto(produtoModel);
        estoqueModel.setQtdEstoqueProduto(estoqueCriarDto.qtdEstoqueProduto());
        EstoqueModel salvar = estoqueRepository.save(estoqueModel);

        return new EstoqueCriadoRespostaDto(
            salvar.getCdEstoque(),
                salvar.getProduto().getCdProduto(),
                salvar.getProduto().getNmProduto(),
                salvar.getQtdEstoqueProduto()
        );

    }

    public List<ListarEstoqueResponseDto> listarEstoque(){
        return estoqueRepository.findAll()
                .stream()
                .map(estoqueModel -> new ListarEstoqueResponseDto(
                        estoqueModel.getCdEstoque(),
                        estoqueModel.getQtdEstoqueProduto(),
                        estoqueModel.getFlAtivo(),
                        estoqueModel.getProduto().getCdProduto(),
                        estoqueModel.getProduto().getNmProduto()
                ))
                .toList();
    }
}