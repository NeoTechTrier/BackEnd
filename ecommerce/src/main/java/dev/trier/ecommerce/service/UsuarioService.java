package dev.trier.ecommerce.service;

import dev.trier.ecommerce.dto.usuario.criacao.UsuarioCriarDto;
import dev.trier.ecommerce.dto.usuario.criacao.UsuarioResponseDto;
import dev.trier.ecommerce.model.UsuarioModel;
import dev.trier.ecommerce.model.enums.UsersRole;
import dev.trier.ecommerce.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Transactional
    public UsuarioResponseDto criarUsuario(UsuarioCriarDto usuarioCriarDto){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUserRole(UsersRole.valueOf("USER"));
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
        UsuarioModel salvo = usuarioRepository.save(usuarioModel);

        return new UsuarioResponseDto(
                salvo.getNmCliente(),
                salvo.getDsEmail(),
                salvo.getNuTelefone(),
                salvo.getDsCidade(),
                salvo.getFlAtivo()
        );
    }

    public Optional<UsuarioResponseDto> listarCdUsuario(Integer cdUsuario){
        return usuarioRepository.findByCdUsuario(cdUsuario)
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getNmCliente(),
                        usuario.getDsEmail(),
                        usuario.getNuTelefone(),
                        usuario.getDsCidade(),
                        usuario.getFlAtivo()
                ));
    }


    //Deu Certo
    public List<UsuarioResponseDto> listarUsuarios(){
        return usuarioRepository.findAll() 
                .stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getNmCliente(),
                        usuario.getDsEmail(),
                        usuario.getNuTelefone(),
                        usuario.getDsCidade(),
                        usuario.getFlAtivo()
                ))
                .collect(Collectors.toList()) ;
    }

    public Optional<UsuarioResponseDto> listarUsuarioNome(String nmCliente){
       return usuarioRepository.findByNmCliente(nmCliente)
               .map(usuario -> new UsuarioResponseDto(
                       usuario.getNmCliente(),
                       usuario.getDsEmail(),
                       usuario.getNuTelefone(),
                       usuario.getDsCidade(),
                       usuario.getFlAtivo()
               ));
               // .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário " + nmCliente + " não encontrado."));
    }

    public Optional<UsuarioResponseDto> listarUsuarioCPF(String nuCPF){
        return  usuarioRepository.findByNuCPF(nuCPF)
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getNmCliente(),
                        usuario.getDsEmail(),
                        usuario.getNuTelefone(),
                        usuario.getDsCidade(),
                        usuario.getFlAtivo()
                ));
       // .orElseThrow(() -> new RecursoNaoEncontradoException(nuCPF + "não encontrado."));

    }
}
