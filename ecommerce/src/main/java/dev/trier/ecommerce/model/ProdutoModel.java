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
    @Schema(description = "Identificador único", example = "1")
    private Integer cdProduto;

    @Schema(description = "Nome do produto", example = "NVIDIA RTX 4070S")
    private String nmProduto;

    @Schema(description = "Valor do produto sem letras", example = "47.50")
    private Double vlProduto;

    @Schema(description = "Categoria do produto", example = "Placa de video")
    @Enumerated(EnumType.STRING)
    private CategoriaProduto dsProduto;

    @Lob
    @Schema(description = "Imagem do produto para inserção", example = "Bytes da imagem")
    private byte[] imgProduto;

    @Schema(description = "Se produto está ativo ou inativado no sistema", example = "S está ativo")
    private String flAtivo = "S";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cdEmpresa")
    @Schema(description = "Código da empresa que fabrica/fornce o produto", example = "Nvidia")
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "A qual estoque pertence o produto", example = "1")
    private List<EstoqueModel> estoques;

    @OneToMany(mappedBy = "produto")
    @Schema(description = "A qual item pedido pertence ", example = "1")
    private List<ItemPedidoModel> itensPedido;
}
