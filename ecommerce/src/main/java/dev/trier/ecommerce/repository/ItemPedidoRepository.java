package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {
    Optional<ItemPedidoModel> findByCdItemPedido(Integer cdItemPedido);
   // Optional<ItemPedidoModel> findByCdProduto(Integer cdProduto);
   // Optional<ItemPedidoModel> findByCdPedido(Integer cdPedido);
}
