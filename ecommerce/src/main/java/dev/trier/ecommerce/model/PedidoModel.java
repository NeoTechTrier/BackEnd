package dev.trier.ecommerce.model;

import dev.trier.ecommerce.model.enums.FormaPagamento;
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
@Table(name = "TBPEDIDO")
@Schema(description = "Criação do pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do pedido", example = "1")
    private Integer cdPedido;

    @ManyToOne
    @JoinColumn(name = "cdUsuario")
    @Schema(description = "Usuário que realizou o pedido")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Forma de pagamento do pedido", example = "PIX")
    private FormaPagamento formaPagamento;

    @Schema(description = "Valor do frete aplicado ao pedido", example = "15.50")
    private Double vlFrete;

    @Schema(description = "Valor total do pedido incluindo frete", example = "200.00")
    private Double vlTotalPedido;

    @Schema(description = "Flag indicando se o pedido está ativo ou inativado", example = "S")
    private String flAtivo = "S";

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de itens contidos no pedido")
    private List<ItemPedidoModel> itensPedido;
}
