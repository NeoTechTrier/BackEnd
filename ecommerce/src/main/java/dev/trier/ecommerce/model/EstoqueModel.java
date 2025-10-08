package dev.trier.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBESTOQUE")
@Schema(description = "Criação do estoque")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private Integer cdEstoque;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cdProduto")
    private ProdutoModel produto;

    private Integer qtdEstoqueProduto;

    private String flAtivo = "S";
}
