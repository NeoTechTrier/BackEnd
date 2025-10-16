package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.usuario.criacao.UsuarioCriarDto;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Capacidade de criação e modificação do usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(path = "/criar")
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody @Valid UsuarioCriarDto usuarioCriarDto) {
        UsuarioModel usuarioCriado = usuarioService.criarUsuario(usuarioCriarDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCriado);
    }

    @GetMapping(path = "/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        var lista = usuarioService.listarUsuarios();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }


    @GetMapping("/{nmCliente}")
    public ResponseEntity<?> listarUsuarioNome(@PathVariable String nmCliente) {
        UsuarioModel usuarioModel = usuarioService.listarUsuarioNome(nmCliente);
        return ResponseEntity.ok(usuarioModel);
    }
}
