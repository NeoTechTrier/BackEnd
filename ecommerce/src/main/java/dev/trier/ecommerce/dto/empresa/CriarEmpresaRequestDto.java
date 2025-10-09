package dev.trier.ecommerce.dto.empresa;

//Dto para cadastro e outros...
public record CriarEmpresaRequestDto(
        String nmFantasia,
        String nmRazao,
        String nuCNPJ,
        String nuTelefone,
        String dsEndereco,
        String nuEndereco,
        String flAtivo
) {
}
