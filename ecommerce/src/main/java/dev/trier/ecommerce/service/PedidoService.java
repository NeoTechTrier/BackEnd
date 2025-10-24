package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.pedido.criacao.ListarPedidosResponseDto;
import dev.trier.ecommerce.dto.pedido.criacao.PedidoCriarDto;
import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.repository.PedidoRepository;
import dev.trier.ecommerce.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    //Metodo Criar Pedido
    public PedidoModel criarPedido(PedidoCriarDto pedidoCriarDto){
        UsuarioModel usarioModel = usuarioRepository.findById(pedidoCriarDto.cdUsuario()).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado para o código: " + pedidoCriarDto.cdUsuario()) // Procura usuário antes de criar o pedidoCriarDto
        );

        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setUsuario(usarioModel);
        pedidoModel.setFormaPagamento(pedidoCriarDto.formaPagamento());
        pedidoModel.setVlFrete(pedidoCriarDto.vlFrete());
        pedidoModel.setVlTotalPedido(pedidoCriarDto.vlTotalPedido());


        return pedidoRepository.save(pedidoModel);
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

    //Metodo Finalizar Pedido


    //Metodo Retirar Produto do Carrinho (Verificar se é em Pedido ou ItemPedido)
}
