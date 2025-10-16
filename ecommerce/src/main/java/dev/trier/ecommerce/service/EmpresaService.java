package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriadaRespostaDto;
import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriarDto;
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
    public EmpresaCriadaRespostaDto criarEmpresa(EmpresaCriarDto dto) {
        EmpresaModel empresaModel = new EmpresaModel();
        empresaModel.setNmFantasia(dto.nmFantasia());
        empresaModel.setNmRazao(dto.nmRazao());
        empresaModel.setNuCNPJ(dto.nuCNPJ());
        empresaModel.setNuTelefone(dto.nuTelefone());
        empresaModel.setDsCidade(dto.dsCidade());
        empresaModel.setDsEstado(dto.dsEstado());
        empresaModel.setDsEndereco(dto.dsEndereco());
        empresaModel.setNuEndereco(dto.nuEndereco());
        EmpresaModel salvar = empresaRepository.save(empresaModel);



        return new EmpresaCriadaRespostaDto(
                salvar.getNmFantasia(),
                salvar.getNmRazao(),
                salvar.getNuCNPJ()
        );
    }

    public List<EmpresaModel> listarTodos(){
        return empresaRepository.findAll();
    }
}
