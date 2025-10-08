package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.produto.Criacao.CriarProdutoRequestDto;
import dev.trier.ecommerce.dto.produto.Criacao.CriarProdutoResponseDto;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.repository.ProdutoRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    public final ProdutoRespository produtoRespository;

    public ProdutoService(ProdutoRespository produtoRespository) {
        this.produtoRespository = produtoRespository;
    }

    public List<ProdutoModel> buscarProdutos(){
        return produtoRespository.findAll();
    };

    public CriarProdutoResponseDto cadastrarProduto(CriarProdutoRequestDto requestDto){
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNmProduto(requestDto.nmProduto());
        produtoModel.setVlProduto(requestDto.vlProduto());
        produtoModel.setDsProduto(requestDto.dsProduto());
    }

}
