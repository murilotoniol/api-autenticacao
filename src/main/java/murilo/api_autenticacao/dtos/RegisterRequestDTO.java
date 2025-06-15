package murilo.api_autenticacao.dtos;
import murilo.api_autenticacao.models.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String name;
    private String email;
    private String password;
    private Role role;
}
