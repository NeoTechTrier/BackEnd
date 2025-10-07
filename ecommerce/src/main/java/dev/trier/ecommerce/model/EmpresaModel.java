package dev.trier.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBEMPRESA")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdEmpresa;

    private String nmFantasia;

    private String nmRazao;

    private String nuCPNJ;

    private Integer nuTelefone;

    private String dsEndereco;

    private String nuEndereco;

    private String flAtivo = "S";

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoModel> produtos;
}
