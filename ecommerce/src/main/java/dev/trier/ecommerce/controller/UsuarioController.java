package dev.trier.ecommerce.controller;


import dev.trier.ecommerce.dto.usuario.CreateUsuarioRequest;
import dev.trier.ecommerce.dto.usuario.CreateUsuarioResponse;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    //instanciamento
    public final UsuarioService usuarioService;
    //Construtor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    //Salvar
    @PostMapping("/cadastrar") //Passo criar classes especifica para Request e Response no DTO;
    public ResponseEntity<CreateUsuarioResponse> cadastrarUsuario(@Valid @RequestBody  CreateUsuarioRequest createUsuarioRequest) {
        CreateUsuarioResponse response = usuarioService.cadastrarUsuario(createUsuarioRequest);
        return ResponseEntity.status(201).body(response);
    }



    //Buscar Todos
    @GetMapping
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }


}
