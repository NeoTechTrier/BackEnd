package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.itempedido.criacao.ItemPedidoCriadoRespostaDto;
import dev.trier.ecommerce.dto.itempedido.criacao.ItemPedidoCriarDto;
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

    @Transactional
    public ItemPedidoCriadoRespostaDto criarItemPedido(ItemPedidoCriarDto itemPedidoCriarDto) {
        ProdutoModel produtoModel = produtoRespository.findById(itemPedidoCriarDto.cdProduto())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado para o código: " + itemPedidoCriarDto.cdProduto()));  //Procura cdProduto antes de criar no ItemPedido
        PedidoModel pedidoModel = pedidoRepository.findById(itemPedidoCriarDto.cdPedido())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado para o código: " + itemPedidoCriarDto.cdPedido()));  //Procura cdPedido antes de criar no ItemPedido

        ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
        itemPedidoModel.setProduto(produtoModel);
        itemPedidoModel.setPedido(pedidoModel);
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

    public List<ItemPedidoModel> listaItemPedidos() {
        return itemPedidoRepository.findAll();
    }
}
