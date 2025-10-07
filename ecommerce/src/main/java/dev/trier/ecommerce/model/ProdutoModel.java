package dev.trier.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TBPRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdProduto;

    private String nmProduto;

    private Double vlProduto;

    private String dsProduto;

    @Lob
    private byte[] imgProduto;

    private String flAtivo = "S";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cdEmpresa")
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstoqueModel> estoques;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedidoModel> itensPedido;
}
