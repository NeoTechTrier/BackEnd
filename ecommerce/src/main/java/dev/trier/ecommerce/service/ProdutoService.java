package dev.trier.ecommerce.service;


import dev.trier.ecommerce.dto.produto.criacao.CriarProdutoResponseDto;
import dev.trier.ecommerce.dto.produto.request.UpdateRequestDto;

import dev.trier.ecommerce.dto.produto.response.*;
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
import java.util.stream.Collectors;
import dev.trier.ecommerce.utils.Utils;

@AllArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRespository produtoRespository;
    private final EmpresaRepository empresaRepository;

    @Transactional
    public CriarProdutoResponseDto criarProduto(ProdutoCriarDto produtoCriarDto) {
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
        ProdutoModel salvo = produtoRespository.save(produtoModel);
        return new CriarProdutoResponseDto(
                salvo.getNmProduto(),
                salvo.getVlProduto(),
                salvo.getDsCategoria(),
                salvo.getDsProduto(),
                salvo.getImgProduto(),
                salvo.getCdProduto()
        );
    }

    /*
    @Transactional
    public CriarProdutoResponseDto criarProduto(ProdutoCriarDto produtoCriarDto) {
        EmpresaModel empresaModel = empresaRepository.findById(produtoCriarDto.cdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para o código: " + produtoCriarDto.cdEmpresa()));

        ProdutoModel produtoModel = new ProdutoModel();

        produtoModel.setNmProduto(produtoCriarDto.nmProduto());
        produtoModel.setVlProduto(produtoCriarDto.vlProduto());
        produtoModel.setDsCategoria(CategoriaProduto.valueOf(produtoCriarDto.dsCategoria()));
        produtoModel.setDsProduto(produtoCriarDto.dsProduto());
        produtoModel.setEmpresa(empresaModel);

        MultipartFile imgProduto = produtoCriarDto.imgProduto();
        if (imgProduto != null &&  !imgProduto.isEmpty()) {
            try{
                produtoModel.setImgProduto(imgProduto.getBytes());
            }catch (IOException e){
                throw new RuntimeException("Erro ao processar imagem dp produto", e);
            }
        }
        ProdutoModel salvo =  produtoRespository.save(produtoModel);

        //Alocação de estoque
        //Ideal criar uma verificação de id do Produto
        //É preciso testar para validar contra erros do sistema.
        EstoqueModel estoqueModel = new EstoqueModel();
        System.out.println("Chegou no estoque " +  estoqueModel.getCdEstoque());
        estoqueModel.setProduto(salvo);
        estoqueModel.setQtdEstoqueProduto(produtoCriarDto.qunatidadeEstoque());
        EstoqueModel estoqueModel2 = estoqueRepository.save(estoqueModel);
        return new CriarProdutoResponseDto(
                salvo.getNmProduto(),
                salvo.getVlProduto(),
                salvo.getDsCategoria(),
                salvo.getDsProduto(),
                salvo.getImgProduto()
        );
    }

   public List<ProdutoModel> listarProdutos(){
      return produtoRespository.findAll();
    }
     */


    //Vericar os dados do DTO
    public List<ListarProdutosResponseDto> listarProdutos(){
      return produtoRespository.findAll()
              .stream()
              .map(produto -> new ListarProdutosResponseDto(
                      produto.getNmProduto(),
                      produto.getVlProduto(),
                      produto.getDsCategoria().toString(),
                      produto.getDsProduto(),
                      produto.getImgProduto(),
                      produto.getEmpresa().getCdEmpresa()
              ))
              .collect(Collectors.toList());
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

    //---------------------------------------------------------------------------------

    //Provavel GET busca Imagem
    public ProdutoModel buscarProdutoPorId(Integer cdProduto) {
        return produtoRespository.findByCdProduto(cdProduto)
                .orElseThrow(
                        () -> new RuntimeException("Produto não encontrado"));
    }

    /*
    //Metodos para o endpoint de imagem pelo DTO
    public Optional<ListarImagemCdProdutoDto> lsitarImagemCdProduto(Integer cdProduto) {
        return produtoRespository.findByCdProduto(cdProduto)
                .map(produto -> new ListarImagemCdProdutoDto(
                        produto.getImgProduto()
                ));
    }

     */

    //---------------------------------------------------------------------------------

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

    public UpdateResponseDto atualizarProduto(UpdateRequestDto updateProdutoDto, MultipartFile imgProduto, Integer cdProduto) {
        ProdutoModel produtoModel = produtoRespository.findByCdProduto(cdProduto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + cdProduto));

        // Copia apenas propriedades não nulas (ignora categorias/empresa para tratar separadamente)
        Utils.copyNonNullProperties(updateProdutoDto, produtoModel, "dsCategoria", "cdEmpresa", "cdProduto");

        // tratar categoria
        if (updateProdutoDto.dsCategoria() != null) {
            produtoModel.setDsCategoria(CategoriaProduto.fromString(updateProdutoDto.dsCategoria()));
        }

        // tratar empresa
        if (updateProdutoDto.cdEmpresa() != null) {
            EmpresaModel empresaModel = empresaRepository.findById(updateProdutoDto.cdEmpresa())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada: " + updateProdutoDto.cdEmpresa()));
            produtoModel.setEmpresa(empresaModel);
        }

        // processar imagem recebida como parte multipart
        if (imgProduto != null && !imgProduto.isEmpty()) {
            try {
                produtoModel.setImgProduto(imgProduto.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar imagem do produto", e);
            }
        }

        ProdutoModel salvo = produtoRespository.save(produtoModel);
        return new UpdateResponseDto(
                salvo.getNmProduto(),
                salvo.getVlProduto(),
                salvo.getDsProduto()
        );
    }

    //Verificar uso de Optional ou List
    public Optional<ProdutoNomeResponseDto> listarProdutoNome(String nmProduto) {
        return produtoRespository.findByNmProduto(nmProduto)
                .map(produto -> new ProdutoNomeResponseDto(
                        produto.getNmProduto(),
                        produto.getVlProduto(),
                        produto.getDsCategoria(),
                        produto.getDsProduto()
                ));
    }
    //public List<ProdutoCategoriaResponseDto> listarProdutoCategoria()

}
