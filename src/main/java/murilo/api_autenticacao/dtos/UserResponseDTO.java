package murilo.api_autenticacao.dtos;

import lombok.Data;
import murilo.api_autenticacao.models.Role;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Role role;
}
