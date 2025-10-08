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
@Schema(description = "Criação do estoque para o produto")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do estoque", example = "1")
    private Integer cdEstoque;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cdProduto")
    @Schema(description = "Produto associado a este estoque")
    private ProdutoModel produto;

    @Schema(description = "Quantidade disponível do produto no estoque", example = "50")
    private Integer qtdEstoqueProduto;

    @Schema(description = "Flag indicando se o estoque está ativo ou inativo", example = "S")
    private String flAtivo = "S";
}
