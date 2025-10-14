package dev.trier.ecommerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBPRODUTO")

public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private Integer cdItemPedido;

    @ManyToOne
    @Schema(description = "Identificador único", example = "1")
    @JoinColumn(name = "cdPedido")
    private PedidoModel pedido;

    @ManyToOne
    @Schema(description = "Identificador único", example = "1")
    @JoinColumn(name = "cdProduto")
    private ProdutoModel produto;

    @Column(nullable = false)
    private Double vlItemPedido;

    private Integer qtItem;





}
