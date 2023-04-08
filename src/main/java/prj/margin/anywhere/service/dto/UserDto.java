package prj.margin.anywhere.service.dto;

import lombok.Getter;
import lombok.Setter;
import prj.margin.anywhere.domain.Role;

@Getter @Setter
public class UserDto {
    private String name;
    private String password;
    private Role role;
    private String email;
}
