package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriadaRespostaDto;
import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriarDto;
import dev.trier.ecommerce.dto.empresa.modificacao.UpdateEmpresaDto;
import dev.trier.ecommerce.dto.empresa.response.EmpresaListResponseDto;
import dev.trier.ecommerce.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa", description = "Capacidade de criação e modificação da empresa")
@CrossOrigin("*")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    @PostMapping("/criar")
    public ResponseEntity<EmpresaCriadaRespostaDto> criarEmpresa(@RequestBody @Valid EmpresaCriarDto empresaCriarDto){
        EmpresaCriadaRespostaDto empresaCriado = empresaService.criarEmpresa(empresaCriarDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaCriado);
    }

    @GetMapping("/listar/todos")
    @Transactional
    public ResponseEntity<List<EmpresaListResponseDto>> listar(){
        var lista = empresaService.listarTodos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    //Get com possivel unutilidade, verificar em que situação será necessario
    @GetMapping("/listarCNPJ/{nuCNPJ}")
    public ResponseEntity<EmpresaListResponseDto> listarEmpresaCNPJ(@PathVariable String nuCNPJ){
        EmpresaListResponseDto empresa = empresaService.listarEmpresaCNPJ(nuCNPJ);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/update/{cdEmpresa}")
    public ResponseEntity<EmpresaCriadaRespostaDto> atualizarEmpresa(@PathVariable Integer cdEmpresa,
                                                                     @RequestBody @Valid UpdateEmpresaDto updateEmpresaDto) {
        try {
            EmpresaCriadaRespostaDto atualizado = empresaService.atualizarEmpresa(cdEmpresa, updateEmpresaDto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
