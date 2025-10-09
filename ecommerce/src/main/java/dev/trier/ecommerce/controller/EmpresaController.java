package dev.trier.ecommerce.controller;


import dev.trier.ecommerce.dto.empresa.CriarEmpresaRequestDto;
import dev.trier.ecommerce.dto.empresa.CriarEmpresaResponseDto;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<EmpresaModel> buscarEmpresas(){
        return empresaService.buscarEmpresas();
    }

    /*
    @PostMapping("/cadastrar")
    public ResponseEntity<CriarEmpresaResponseDto> cadastrarEmpresa(@RequestBody CriarEmpresaRequestDto criarEmpresaRequestDto){
        CriarEmpresaResponseDto criar = empresaService.cadastrarEmpresa(criarEmpresaRequestDto);
        return ResponseEntity.status(201).body(criar);
    }

     */

}
