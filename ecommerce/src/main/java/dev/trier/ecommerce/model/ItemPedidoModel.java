package dev.trier.ecommerce.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @JoinColumn(name = "cdPedido")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "cdProduto")
    private ProdutoModel produto;

    private Double vlItemPedido;

    private Integer qtItem;





}
