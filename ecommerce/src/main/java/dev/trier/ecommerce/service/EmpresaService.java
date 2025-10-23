package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriadaRespostaDto;
import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriarDto;
import dev.trier.ecommerce.dto.empresa.criacao.response.ListarEmpresasResponseDto;
import dev.trier.ecommerce.exceptions.RecursoNaoEncontradoException;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Transactional
    public EmpresaCriadaRespostaDto criarEmpresa(EmpresaCriarDto empresadto) {
        EmpresaModel empresaModel = new EmpresaModel();
        empresaModel.setNmFantasia(empresadto.nmFantasia());
        empresaModel.setNmRazao(empresadto.nmRazao());
        empresaModel.setNuCNPJ(empresadto.nuCNPJ());
        empresaModel.setNuTelefone(empresadto.nuTelefone());
        empresaModel.setDsCidade(empresadto.dsCidade());
        empresaModel.setDsEstado(empresadto.dsEstado());
        empresaModel.setDsEndereco(empresadto.dsEndereco());
        empresaModel.setNuEndereco(empresadto.nuEndereco());
        EmpresaModel salvar = empresaRepository.save(empresaModel);



        return new EmpresaCriadaRespostaDto(
                salvar.getNmFantasia(),
                salvar.getNmRazao(),
                salvar.getNuCNPJ()
        );
    }

    public List<ListarEmpresasResponseDto> listarTodos(){
        return empresaRepository.findAll()
                .stream()
                .map(empresa -> new ListarEmpresasResponseDto(
                        empresa.getNmFantasia(),
                        empresa.getNmRazao(),
                        empresa.getNuCNPJ(),
                        empresa.getNuTelefone(),
                        empresa.getDsEstado(),
                        empresa.getDsEndereco(),
                        empresa.getNuEndereco()
                ))
                .toList();
    }


    public EmpresaModel listarEmpresaCNPJ(String nuCNPJ){
        return empresaRepository.findByNuCNPJ(nuCNPJ)
                .orElseThrow(() -> new RecursoNaoEncontradoException("CNPJ "+ nuCNPJ + " não encontrado."));
    }

}
