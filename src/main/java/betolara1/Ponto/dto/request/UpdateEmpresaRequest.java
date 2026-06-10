package betolara1.Ponto.dto.request;

import lombok.Data;

@Data
public class UpdateEmpresaRequest {
    private String nomeEmpresa;
    private String cnpj;
    private String endereco;
    private String cep;
    private String bairro;
    private String cidade;
    private Boolean isActive;
}
