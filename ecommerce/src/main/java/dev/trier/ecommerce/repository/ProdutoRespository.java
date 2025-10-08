package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRespository extends JpaRepository<ProdutoModel, Integer> {
}
