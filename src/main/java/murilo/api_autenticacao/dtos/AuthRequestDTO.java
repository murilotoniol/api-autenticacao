package murilo.api_autenticacao.dtos;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String email;
    private String password;

}
