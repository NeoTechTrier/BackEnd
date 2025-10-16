package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.usuario.criacao.UsuarioCriarDto;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioModel criarUsuario(UsuarioCriarDto usuarioCriarDto){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setNmCliente(usuarioCriarDto.nmCliente());
        usuarioModel.setDsEmail(usuarioCriarDto.dsEmail());
        usuarioModel.setDsSenha(usuarioCriarDto.dsSenha());
        usuarioModel.setNuCPF(usuarioCriarDto.nuCPF());
        usuarioModel.setNuRG(usuarioCriarDto.nuRG());
        usuarioModel.setDsCidade(usuarioCriarDto.dsCidade());
        usuarioModel.setDsEstado(usuarioCriarDto.dsEstado());
        usuarioModel.setNuTelefone(usuarioCriarDto.nuTelefone());
        usuarioModel.setDsEndereco(usuarioCriarDto.dsEndereco());
        usuarioModel.setNuEndereco(usuarioCriarDto.nuEndereco());

        return usuarioRepository.save(usuarioModel);
    }

    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }
}
