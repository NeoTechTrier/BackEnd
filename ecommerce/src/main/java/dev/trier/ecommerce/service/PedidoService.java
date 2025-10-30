package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.itempedido.criacao.ItemPedidoCriarDto;
import dev.trier.ecommerce.dto.pedido.criacao.ListarPedidosResponseDto;
import dev.trier.ecommerce.dto.pedido.criacao.PedidoCriarDto;
import dev.trier.ecommerce.dto.pedido.criacao.PedidoCriarResponseDto;
import dev.trier.ecommerce.exceptions.RecursoNaoEncontradoException;
import dev.trier.ecommerce.model.ItemPedidoModel;
import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.model.ProdutoModel;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.repository.PedidoRepository;
import dev.trier.ecommerce.repository.ProdutoRespository;
import dev.trier.ecommerce.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRespository produtoRespository;
    private final EstoqueService estoqueService;

    //Metodo Criar Pedido
    @Transactional
    public PedidoCriarResponseDto criarPedido(PedidoCriarDto pedidoCriarDto){
        UsuarioModel usarioModel = usuarioRepository.findById(pedidoCriarDto.cdUsuario()).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado para o código: " + pedidoCriarDto.cdUsuario()) // Procura usuário antes de criar o pedidoCriarDto
        );

        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setUsuario(usarioModel);
        pedidoModel.setFormaPagamento(pedidoCriarDto.formaPagamento());
        pedidoModel.setVlFrete(pedidoCriarDto.vlFrete());
        pedidoModel.setVlTotalPedido(pedidoCriarDto.vlTotalPedido());

        PedidoModel salvo = pedidoRepository.save(pedidoModel);

        return new PedidoCriarResponseDto(
                salvo.getCdPedido(),
                salvo.getFormaPagamento(),
                salvo.getVlFrete(),
                salvo.getVlTotalPedido()
        );
    }

    //Metodo Listar Pedidos //SOMENTE PARA ADMIN
    public List<ListarPedidosResponseDto> listarPedidos(){
        return pedidoRepository.findAll()
                .stream()
                .map(pedidos-> new ListarPedidosResponseDto(
                        pedidos.getUsuario().getCdUsuario(),
                        pedidos.getFormaPagamento(),
                        pedidos.getVlFrete(),
                        pedidos.getVlTotalPedido()
                ))
                .toList();
    }
}
