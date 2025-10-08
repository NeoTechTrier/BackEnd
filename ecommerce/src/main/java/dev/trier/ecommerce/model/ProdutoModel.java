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
@Schema(description = "Criação do produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private Integer cdProduto;

    @Schema(description = "Nome do produto", example = "NVIDIA RTX 4070S")
    @Column(nullable = false)
    private String nmProduto;

    @Schema(description = "Valor do produto sem letras", example = "47.50")
    @Column(nullable = false)
    private Double vlProduto;

    @Schema(description = "Categoria do produto", example = "Placa de video")
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
    @Schema(description = "Código da empresa que fabrica/fornece o produto", example = "Nvidia")
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "A qual estoque pertence o produto", example = "1")
    private List<EstoqueModel> estoques;

    @OneToMany(mappedBy = "produto")
    @Schema(description = "A qual item pedido pertence ", example = "1")
    private List<ItemPedidoModel> itensPedido;
}
