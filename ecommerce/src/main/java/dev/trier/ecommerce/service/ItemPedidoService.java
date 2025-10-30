package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.estoque.criacao.ListarEstoqueResponseDto;
import dev.trier.ecommerce.dto.itempedido.criacao.ItemPedidoCriadoRespostaDto;
import dev.trier.ecommerce.dto.itempedido.criacao.ItemPedidoCriarDto;
import dev.trier.ecommerce.dto.itempedido.criacao.ListarItensPedidosResponseDto;
import dev.trier.ecommerce.model.EstoqueModel;
import dev.trier.ecommerce.model.ItemPedidoModel;
import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.repository.EstoqueRepository;
import dev.trier.ecommerce.repository.ItemPedidoRepository;
import dev.trier.ecommerce.repository.PedidoRepository;
import dev.trier.ecommerce.repository.ProdutoRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRespository produtoRespository;
    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;
    private final EstoqueService estoqueService;


    @Transactional
    public ItemPedidoCriadoRespostaDto criarItemPedido(ItemPedidoCriarDto itemPedidoCriarDto) {

        ProdutoModel produtoModel = produtoRespository.findById(itemPedidoCriarDto.cdProduto())
                .orElseThrow( //Procura cdProduto antes de criar no ItemPedido
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para o código: " + itemPedidoCriarDto.cdProduto()));
        PedidoModel pedidoModel = pedidoRepository.findById(itemPedidoCriarDto.cdPedido())
                .orElseThrow( //Procura cdPedido antes de criar no ItemPedido
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado para o código: " + itemPedidoCriarDto.cdPedido()));
       // EstoqueModel estoqueModel = estoqueRepository.findByCdProduto(itemPedidoCriarDto.cdProduto())
         //       .orElseThrow( //Verifica quantidade de item Produto no estoque
           //             ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque do produto" + itemPedidoCriarDto.cdProduto() + " esgotado"));
        //----------------------------------------------------------------------------


        estoqueService.diminuirEstoqueProduto(itemPedidoCriarDto.cdProduto(),itemPedidoCriarDto.qtItem());

        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setProduto(produtoModel);
        itemPedidoModel.setPedido(pedidoModel);
        itemPedidoModel.setVlItemPedido(itemPedidoCriarDto.vlItemPedido());
        itemPedidoModel.setQtItem(itemPedidoCriarDto.qtItem());

        ItemPedidoModel salvar=  itemPedidoRepository.save(itemPedidoModel);

        return new ItemPedidoCriadoRespostaDto(
                salvar.getCdItemPedido(),
                salvar.getPedido().getCdPedido(),
                salvar.getProduto().getCdProduto(),
                salvar.getVlItemPedido(),
                salvar.getQtItem()

        );
    }

    public List<ListarItensPedidosResponseDto> listaItemPedidos() {
        return itemPedidoRepository.findAll()
                .stream()
                .map(itemPedidoModel -> new ListarItensPedidosResponseDto(
                        itemPedidoModel.getPedido().getCdPedido(),
                        itemPedidoModel.getVlItemPedido(),
                        itemPedidoModel.getQtItem()
                ))
                .toList();
    }

}
