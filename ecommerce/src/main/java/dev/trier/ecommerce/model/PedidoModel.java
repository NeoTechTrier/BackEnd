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
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private Integer cdPedido;

    @ManyToOne
    @JoinColumn(name = "cdUsuario")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Double vlFrete;

    private Double vlTotalPedido;

    private String flAtivo = "S";

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoModel> itensPedido;
}
