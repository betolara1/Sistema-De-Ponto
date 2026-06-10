package betolara1.Ponto.dto;

import java.time.LocalDateTime;

import betolara1.Ponto.model.Empresa;

public record EmpresaDTO(
        Long id,
        String nomeEmpresa,
        String cnpj,
        String endereco,
        String cep, 
        String bairro, 
        String cidade, 
        Boolean isActive,
        LocalDateTime dateCreated, 
        LocalDateTime dateUpdated
    ) {

    public EmpresaDTO(Empresa empresa){
        this(
            empresa.getId(),
            empresa.getNomeEmpresa(),
            empresa.getCnpj(),
            empresa.getEndereco(),
            empresa.getCep(),
            empresa.getBairro(),
            empresa.getCidade(),
            empresa.getIsActive(),
            empresa.getDateCreated(),
            empresa.getDateUpdated()
        );
    }
}
