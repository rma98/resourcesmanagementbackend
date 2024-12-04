package br.edu.ifpe.manager.repository;

import br.edu.ifpe.manager.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    // Find resources by name
    List<Resource> findByName(String name);
    
    // Find resources by status
    List<Resource> findByStatus(String status);  // Here 'status' is stored as a String in the DTO
    
    // Find resources by location
    List<Resource> findByLocation(String location);
    
    // Find resources by name, status, and location (combination of filters)
    List<Resource> findByNameAndStatusAndLocation(String name, String status, String location);
    
    // Optionally, if you want case-insensitive search for name, status, or location
    List<Resource> findByNameIgnoreCaseContaining(String name);
    List<Resource> findByStatusIgnoreCaseContaining(String status);
    List<Resource> findByLocationIgnoreCaseContaining(String location);
}
