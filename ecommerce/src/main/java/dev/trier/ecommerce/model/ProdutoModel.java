package dev.trier.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.trier.ecommerce.model.enums.CategoriaProduto;
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
@Table(name = "TBPRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdProduto;

    @Column(nullable = false)
    private String nmProduto;

    @Column(nullable = false)
    private Double vlProduto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProduto dsCategoria;

    @Schema(description = "Descrição do produto", example = "Placa de vídeo da nvidia feito...")
    @Column(nullable = false)
    private String dsProduto;

    @Lob
    @Schema(description = "Imagem do produto para inserção")
    @Column(nullable = false)
    private byte[] imgProduto;

    @Schema(description = "Flag indicando se o produto está ativo ou inativado", example = "'S' = ativo ")
    @Column(nullable = false)
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
