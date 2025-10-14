package dev.trier.ecommerce.model;


import dev.trier.ecommerce.model.enums.UsersRole;
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
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdUsuario;

    @Enumerated(EnumType.STRING) // Tem que colocar ou admin ou user no banco
    private UsersRole userRole;

    private String nmCliente;

    private String dsEmail;

    private String dsSenha;

    private String nuCPF;

    private Integer nuTelefone;

    private String nuRG;

    private String dsCidade;

    private String dsEstado;

    private String dsEndereco;

    private String nuEndereco;

    private String flAtivo = "S";

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoModel> pedidos;
}
