package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.usuario.criacao.UsuarioCriarDto;
import dev.trier.ecommerce.dto.usuario.criacao.UsuarioResponseDto;
import dev.trier.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Capacidade de criação e modificação do usuário")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(path = "/criar")
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody @Valid UsuarioCriarDto usuarioCriarDto) {
        UsuarioResponseDto usuarioCriado = usuarioService.criarUsuario(usuarioCriarDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCriado);
    }

    /*
    @GetMapping(path = "/listar/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        var lista = usuarioService.listarUsuarios();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }
     */

    //Funcioando
    @CrossOrigin
    @GetMapping(path = "/listar/usuarios")
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }


/*
 public ResponseEntity<List<ProdutoDto>> findAll() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }
 */



    //IMCOMPLETO
    @CrossOrigin
    @GetMapping("/{cdCliente}")
    public ResponseEntity<Optional<UsuarioResponseDto>>  listarCdUsuario(@PathVariable Integer cdCliente) {
        return ResponseEntity.ok().body(usuarioService.listarCdUsuario(cdCliente));
    }


    //<<<<<<<VERIFICAR ESSAS DUAS SEQUENCIAS DE ENDPOINT>>>>>>>>
    //Talvez seja inutil,
    @CrossOrigin
    @GetMapping("/nome/{nmCliente}")
    public ResponseEntity<?> listarUsuarioNome(@PathVariable String nmCliente) {
        Optional<UsuarioResponseDto> usuario = usuarioService.listarUsuarioNome(nmCliente);
        return ResponseEntity.ok(usuario);
    }
}
