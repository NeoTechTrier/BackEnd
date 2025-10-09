package dev.trier.ecommerce.repository;

import dev.trier.ecommerce.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    //Verificação de existencia de cpf ao cadastrar novo usuario.
    boolean existsByNuCPF(String nuCPF);
    //List<UsuarioModel> findByNuCPF(String nuCPF);
}
