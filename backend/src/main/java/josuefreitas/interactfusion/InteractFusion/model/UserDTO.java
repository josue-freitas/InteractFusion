package josuefreitas.interactfusion.InteractFusion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    //unique username of the user
    @NotNull
    public String userName;

    //actual name of the user
    public String name;

    //biographic information
    public String bio;

    //email of the user
    @NotNull
    public String email;

    //password of the user
    @NotNull
    public String password;

    //cellphone of the user
    public String cellPhone;

    //date of birth of the user
    public Date dob;
}
