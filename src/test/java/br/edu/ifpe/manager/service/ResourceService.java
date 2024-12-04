package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Resource;
import br.edu.ifpe.manager.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    // Admin creates a new resource
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Admin updates an existing resource
    public Resource updateResource(Long id, Resource resourceDetails) {
        Optional<Resource> existingResource = resourceRepository.findById(id);
        
        if (existingResource.isPresent()) {
            Resource resource = existingResource.get();
            resource.setName(resourceDetails.getName());
            resource.setDescription(resourceDetails.getDescription());
            resource.setCapacity(resourceDetails.getCapacity());
            resource.setStatus(resourceDetails.getStatus());
            resource.setLocation(resourceDetails.getLocation());
            return resourceRepository.save(resource);
        } else {
            throw new IllegalArgumentException("Resource with ID " + id + " not found.");
        }
    }

    // Admin deletes a resource
    public void deleteResource(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        if (resource.isPresent()) {
            resourceRepository.delete(resource.get());
        } else {
            throw new IllegalArgumentException("Resource with ID " + id + " not found.");
        }
    }

    // Fetch a resource by its ID
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }
}
