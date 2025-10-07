package dev.trier.ecommerce.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Capacidade de criação e modificação do usuário")
public class UsuarioController {
}
