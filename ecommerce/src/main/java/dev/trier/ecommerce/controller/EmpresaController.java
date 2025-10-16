package dev.trier.ecommerce.controller;


import dev.trier.ecommerce.dto.empresa.CriarEmpresaRequestDto;
import dev.trier.ecommerce.dto.empresa.CriarEmpresaResponseDto;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.repository.EmpresaRepository;
import dev.trier.ecommerce.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa", description = "Capacidade de criação e modificação da empresa")
public class EmpresaController {

    public final EmpresaService empresaService;
    public final EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaService empresaService , EmpresaRepository empresaRepository) {
        this.empresaService = empresaService;
        this.empresaRepository = empresaRepository;
    }


    @GetMapping("/buscar")
    public List<EmpresaModel> buscarEmpresas(){
        return empresaService.buscarEmpresas();
    }


    //Buscar empresa por CdEmpresa
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel>  findById(@PathVariable Integer cdEmpresa){
        EmpresaModel empresa = empresaService.findByCdEmprsa(cdEmpresa);
        return  ResponseEntity.ok().body(empresa);
    }


    //Necessario efetuar teste de funcionalidade;
    @GetMapping("/{nuCNPJ}")
    public ResponseEntity<EmpresaModel>  findByNuCPNJ(@PathVariable String nuCNPJ){
        EmpresaModel empresa = empresaService.findByNuCPNJ(nuCNPJ);
        return  ResponseEntity.ok().body(empresa);
    }

    /*
    @GetMapping("/{cdEmpresa}")
    public List<EmpresaModel> buscarEmpresasPorCdEmpresa(@PathVariable String cdEmpresa){
        return empresaRepository.findByCdEmpresa(cdEmpresa);
    }
     */


    @PostMapping("/cadastrar")
    public ResponseEntity<CriarEmpresaResponseDto> cadastrarEmpresa(@RequestBody CriarEmpresaRequestDto criarEmpresaRequestDto){
        CriarEmpresaResponseDto criar = empresaService.cadastrarEmpresa(criarEmpresaRequestDto);
        return ResponseEntity.status(201).body(criar);
    }

}
