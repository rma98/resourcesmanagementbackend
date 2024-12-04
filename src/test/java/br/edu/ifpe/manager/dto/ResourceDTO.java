package br.edu.ifpe.manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceDTO {
    private Long id;
    private String name;
    private String description;
    private int capacity;
    private String status; // Enum in string form (Available, Unavailable, Under Maintenance)
    private String location;
}
