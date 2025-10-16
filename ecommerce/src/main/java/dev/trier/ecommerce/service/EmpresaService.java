package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.empresa.CriarEmpresaRequestDto;
import dev.trier.ecommerce.dto.empresa.CriarEmpresaResponseDto;
import dev.trier.ecommerce.model.EmpresaModel;
import dev.trier.ecommerce.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    public final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


    public List<EmpresaModel> buscarEmpresas(){
        return empresaRepository.findAll();
    }

    //Buscar empresa pelo CdEmpresa;
    public EmpresaModel findByCdEmprsa(Integer cdEmpresa){
        //Procure um verificador com retorno de existencia, na qual entregara uma mensagem de existencia ou não. Exceções
        Optional<EmpresaModel> empresa = empresaRepository.findByCdEmpresa(cdEmpresa);
        return empresa.orElse(null);
    }


    public EmpresaModel findByNuCPNJ(String nuCPNJ){
        Optional<EmpresaModel> empresa = empresaRepository.findByNuCPNJ(nuCPNJ);
        return empresa.orElse(null);
    }


    public CriarEmpresaResponseDto cadastrarEmpresa( CriarEmpresaRequestDto request){
        //Verificação de CNPJ no Banco
        if (empresaRepository.findByNuCPNJ(request.nuCNPJ()).isPresent()){
            throw new IllegalArgumentException("CNPJ já cadastrado");
        }

        EmpresaModel empresaModel= new EmpresaModel();
        empresaModel.setNmFantasia(request.nmFantasia());
        empresaModel.setNmRazao(request.nmRazao());
        empresaModel.setNuCPNJ(request.nuCNPJ());
        empresaModel.setNuTelefone(request.nuTelefone());
        empresaModel.setDsEndereco(request.dsEndereco());
        empresaModel.setNuEndereco(request.nuEndereco());
        empresaModel.setFlAtivo(request.flAtivo());
        EmpresaModel salvo = empresaRepository.save(empresaModel);

        return new CriarEmpresaResponseDto(
                salvo.getCdEmpresa(),
                salvo.getNmFantasia(),
                salvo.getNuCPNJ(),
                salvo.getFlAtivo()
        );

    }
}
