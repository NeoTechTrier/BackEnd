package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.usuario.CriarUsuarioRequestDto;
import dev.trier.ecommerce.dto.usuario.CriarUsuarioResponseDto;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.PushBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuário", description = "Capacidade de criação e modificação do usuário")
public class UsuarioController {
    public final UsuarioService  usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CriarUsuarioResponseDto> cadastroUsuario(@RequestBody CriarUsuarioRequestDto criarUsuarioRequestDto) {
        CriarUsuarioResponseDto criar = usuarioService.cadastrarUsuario(criarUsuarioRequestDto);
        return ResponseEntity.status(201).body(criar);
    }

    //Precisa criar um DTO especificopara es
    @GetMapping
    public List<UsuarioModel> buscaerUsuario() {
        return usuarioService.buscarUsuarios();
    }

}
