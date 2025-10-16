package dev.trier.ecommerce.dto.empresa;

//MensagemTemporaria: Talvez não seja necessario esse dto response, apenas por teste

public record CriarEmpresaResponseDto(
        Integer cdEmpresa,
        String nmFantasia,
        String nuCNPJ,
        String flAtiva
) {
}
