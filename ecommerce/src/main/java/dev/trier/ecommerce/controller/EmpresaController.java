package dev.trier.ecommerce.controller;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriadaRespostaDto;
import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriarDto;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<EmpresaModel>> listar(){
        var lista = empresaService.listarTodos();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lista);
    }

    //Get com possivel unutilidade, verificar em que situação será necessario
    @GetMapping("/{nuCNPJ}")
    public ResponseEntity<?> listarEmpresaCNPJ(@PathVariable String nuCNPJ){
        EmpresaModel empresaModel= empresaService.listarEmpresaCNPJ(nuCNPJ);
        return ResponseEntity.ok(empresaModel);
    }
}
