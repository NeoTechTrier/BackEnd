package dev.trier.ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBEMPRESA",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nuCPNJ"}),
                @UniqueConstraint(columnNames = {"nmRazao"}),
                @UniqueConstraint(columnNames = {"nuTelefone"})
        })
@Schema(description = "Criação da empresa para vincular nos seus produtos")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da empresa", example = "1")
    private Integer cdEmpresa;

    @Column(nullable = false)
    @Schema(description = "Nome fantasia da empresa", example = "Neo Tech Ltda")
    private String nmFantasia;

    @Column(nullable = false)
    @Schema(description = "Razão social da empresa", example = "Neo Tech Tecnologia Ltda")
    private String nmRazao;

    @Column(nullable = false)
    @Schema(description = "CNPJ da empresa", example = "12.345.678/0001-99")
    private String nuCPNJ;

    @Column(nullable = false)
    @Schema(description = "Telefone da empresa", example = "11987654321")
    private Integer nuTelefone;

    @Column(nullable = false)
    @Schema(description = "Descrição do endereço da empresa", example = "Rua das Flores, Bairro Central")
    private String dsEndereco;

    @Column(nullable = false)
    @Schema(description = "Número do endereço", example = "123")
    private String nuEndereco;


    @Schema(description = "Flag indicando se a empresa está ativa", example = "S")
    private String flAtivo = "S";

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de produtos fornecidos pela empresa")
    private List<ProdutoModel> produtos;
}
