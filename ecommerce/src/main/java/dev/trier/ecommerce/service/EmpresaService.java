package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.empresa.criacao.EmpresaCriarDto;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel criarEmpresa(EmpresaCriarDto dto) {
        EmpresaModel empresaModel = new EmpresaModel();
        empresaModel.setNmFantasia(dto.nmFantasia());
        empresaModel.setNmRazao(dto.nmRazao());
        empresaModel.setNuCPNJ(dto.nuCPNJ());
        empresaModel.setNuTelefone(dto.nuTelefone());
        empresaModel.setDsEndereco(dto.dsEndereco());
        empresaModel.setNuEndereco(dto.nuEndereco());

        try {
            return empresaRepository.save(empresaModel);
        } catch (DataIntegrityViolationException ex) {
            throw ex;
        }
    }
}
