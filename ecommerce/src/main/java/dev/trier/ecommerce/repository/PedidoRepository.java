package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<UsuarioModel,Integer> {
}
