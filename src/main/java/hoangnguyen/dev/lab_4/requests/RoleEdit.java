package hoangnguyen.dev.lab_4.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEdit {
    private Long role_id;
    private String roleName;
}
