package dev.trier.ecommerce.service;

import dev.trier.ecommerce.model.PedidoModel;
import dev.trier.ecommerce.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    public final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoModel> buscarPedidos(){
        return pedidoRepository.findAll();
    }


}
