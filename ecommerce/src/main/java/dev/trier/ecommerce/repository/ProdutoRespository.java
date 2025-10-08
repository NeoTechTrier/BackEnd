package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRespository extends JpaRepository<ProdutoModel, Integer> {
}
