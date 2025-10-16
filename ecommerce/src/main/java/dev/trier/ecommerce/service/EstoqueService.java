package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.estoque.criacao.EstoqueCriarDto;
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
    public EstoqueModel criarEstoque(EstoqueCriarDto estoqueCriarDto) {

        ProdutoModel produtoModel = produtoRespository.findById(estoqueCriarDto.cdProduto())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para o código: " + estoqueCriarDto.cdProduto()));  //Procura cdProduto antes de criar no estoque

        EstoqueModel estoqueModel = new EstoqueModel();
        estoqueModel.setProduto(produtoModel);
        estoqueModel.setQtdEstoqueProduto(estoqueCriarDto.qtdEstoqueProduto());

        return estoqueRepository.save(estoqueModel);

    }

    public List<EstoqueModel> listarEstoque(){
        return estoqueRepository.findAll();
    }
}