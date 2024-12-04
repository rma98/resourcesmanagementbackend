package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Resource;
import br.edu.ifpe.manager.model.ResourceStatus;
import br.edu.ifpe.manager.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceService resourceService;

    private Resource resource;

    @BeforeEach
    public void setUp() {
        resource = Resource.builder()
                .name("Room 101")
                .description("A spacious meeting room")
                .capacity(10)
                .location("Building A")
                .status(ResourceStatus.AVAILABLE)
                .build();
    }

    @Test
    public void testCreateResource() {
        when(resourceRepository.save(resource)).thenReturn(resource);

        Resource createdResource = resourceService.createResource(resource);

        assertNotNull(createdResource);
        assertEquals("Room 101", createdResource.getName());
        verify(resourceRepository, times(1)).save(resource);
    }

    @Test
    public void testUpdateResource() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        Resource updatedResource = resourceService.updateResource(1L, resource);

        assertNotNull(updatedResource);
        assertEquals("Room 101", updatedResource.getName());
    }

    @Test
    public void testDeleteResource() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        resourceService.deleteResource(1L);

        verify(resourceRepository, times(1)).delete(resource);
    }
}
