package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    Optional<PedidoModel> findByCdPedido(Integer cdPedido);
}
