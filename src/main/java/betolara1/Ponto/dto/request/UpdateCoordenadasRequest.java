package betolara1.Ponto.dto.request;

import lombok.Data;

@Data
public class UpdateCoordenadasRequest {
    private String latitude;
    private String longitude;
    private Long empresaId;
}
