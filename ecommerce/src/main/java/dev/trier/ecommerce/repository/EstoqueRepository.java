package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Integer> {
    Optional<EstoqueModel> findByCdEstoque(Integer cdEstoque);
}
