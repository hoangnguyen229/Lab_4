package hoangnguyen.dev.lab_4.requests;

import hoangnguyen.dev.lab_4.models.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private boolean isDeleted;
    private Role role;
}
