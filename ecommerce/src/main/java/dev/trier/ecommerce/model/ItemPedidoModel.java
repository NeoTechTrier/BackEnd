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
    private Integer cdItemPedido;





}
