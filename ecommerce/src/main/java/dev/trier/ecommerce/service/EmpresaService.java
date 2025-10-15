package dev.trier.ecommerce.service;

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
    public EmpresaModel criarEmpresa(EmpresaCriarDto dto) {
        EmpresaModel empresaModel = new EmpresaModel();
        empresaModel.setNmFantasia(dto.nmFantasia());
        empresaModel.setNmRazao(dto.nmRazao());
        empresaModel.setNuCNPJ(dto.nuCNPJ());
        empresaModel.setNuTelefone(dto.nuTelefone());
        empresaModel.setDsEndereco(dto.dsEndereco());
        empresaModel.setNuEndereco(dto.nuEndereco());


        return empresaRepository.save(empresaModel);
    }

    public List<EmpresaModel> listarTodos(){
        return empresaRepository.findAll();
    }
}
