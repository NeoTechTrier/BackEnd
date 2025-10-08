package dev.trier.ecommerce.controller;


import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa", description = "Capacidade de criação e modificação da empresa")
public class EmpresaController {

    public final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/buscar")
    public List<EmpresaModel> buscarProdutos(){
        return empresaService.buscarEmpresas();
    }

}
