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
@Schema(description = "Criação do item pedido")
public class ItemPedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdItemPedido;

    @ManyToOne
    @JoinColumn(name = "cdPedido")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "cdProduto")
    private ProdutoModel produto;

    @Schema(description = "Esse seria o somatório do item pedido", example = "1")
    @Column(nullable = false)
    private Double vlItemPedido;

    @Schema(description = "Aqui vai ser a quantidade de itens para este pedido")
    private Integer qtItem;





}
