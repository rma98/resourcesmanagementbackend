package br.edu.ifpe.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String userType; // Enum in string form (Admin or User)
}
