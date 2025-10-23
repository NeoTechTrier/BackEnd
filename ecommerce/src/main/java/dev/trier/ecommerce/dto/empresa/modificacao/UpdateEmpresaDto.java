package dev.trier.ecommerce.dto.empresa.modificacao;

public record UpdateEmpresaDto(

        String nmFantasia,
        String nmRazao,
        String nuCPNJ,
        String nuTelefone,
        String dsCidade,
        String dsEstado,
        String dsEndereco,
        String nuEndereco
) {
}
