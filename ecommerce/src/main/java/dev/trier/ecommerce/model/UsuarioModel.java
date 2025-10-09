package dev.trier.ecommerce.model;


import dev.trier.ecommerce.model.enums.UsersRole;
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
@Table(name = "TBUSUARIO")
@Schema(description = "Criação do usuário")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único", example = "1")
    private Integer cdUsuario;

    @Enumerated(EnumType.STRING) // Tem que colocar ou admin ou user no banco
    @Schema(description = "Categoria do usuário", example = "User")
    private UsersRole userRole;

    @Schema(description = "Nome do cliente", example = "Davi Beckhauser")
    private String nmCliente;

    @Schema(description = "Número de CPF", example = "112.123.123-22")
    private String nuCPF;

    private String dsEmail;

    private String dsSenha;

    @Schema(description = "Número de telefone da pessoa", example = "48920023805")
    private Integer nuTelefone;

    @Schema(description = "Número de RG da pessoa", example = "7510437")
    private String nuRG;

    @Schema(description = "Endereço de localização de sua moradia", example = "Travessão, estrada geral")
    private String dsEndereco;

    @Schema(description = "Número de residencial da pessoa", example = "102 ou SN")
    private String nuEndereco;

    @Schema(description = "Flag indicando se o usuário está ativo ou inativado", example = "'S' = ativo ")
    private String flAtivo = "S";

    @Schema(description = "Caso usuário tenha pedidos, irá ficar vinculado a ele.", example = "Código do pedido 1")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoModel> pedidos;
}
