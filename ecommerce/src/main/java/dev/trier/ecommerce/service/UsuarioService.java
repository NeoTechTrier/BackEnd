package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.UsuarioDto;
import dev.trier.ecommerce.dto.usuario.CreateUsuarioRequest;
import dev.trier.ecommerce.dto.usuario.CreateUsuarioResponse;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Class Composto pela lógica e regra de negócio

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }


    public Optional<UsuarioModel> buscarPorCodigo(Integer cdUsuario) {
        return usuarioRepository.findById(cdUsuario);
    }


    public CreateUsuarioResponse cadastrarUsuario(CreateUsuarioRequest request){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNmCliente(request.nmCliente());
        usuarioModel.setNuCPF(request.nuCPF());
        usuarioModel.setNuTelefone(request.nuTelefone());
        usuarioModel.setNuRG(request.nuRG());
        usuarioModel.setDsEndereco(request.dsEndereco());
        usuarioModel.setNuEndereco(request.nuEndereco());
        usuarioModel.setFlAtivo(request.flAtivo());
        UsuarioModel salvo = usuarioRepository.save(usuarioModel);

        return  new CreateUsuarioResponse(
                salvo.getCdUsuario(),
                salvo.getNmCliente(),
                salvo.getFlAtivo()
        );
    }
}
