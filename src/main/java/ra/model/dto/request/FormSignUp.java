package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormSignUp {
    @NotBlank(message = "Must not be blank")
    private String fullName;
    @NotBlank(message = "Must not be blank")
    private String email;
    @NotBlank(message = "Must not be blank")
    private String password;
}
