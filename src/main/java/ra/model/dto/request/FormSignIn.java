package ra.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormSignIn {
    @NotBlank(message = "Must not be blank")
    private String email;
    @NotBlank(message = "Must not be blank")
    private String password;
}
