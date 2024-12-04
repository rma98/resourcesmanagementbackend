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
	    Resource resource = Resource.builder()
	            .name("Meeting Room")
	            .description("A room for meetings")
	            .capacity(10)
	            .status(ResourceStatus.AVAILABLE)
	            .location("Floor 1")
	            .build();

	    when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));
	    when(resourceRepository.save(any(Resource.class))).thenAnswer(invocation -> invocation.getArgument(0));

	    Resource updatedResource = resourceService.updateResource(1L, resource);
	    assertNotNull(updatedResource); // Ensure the updated resource is not null
	    assertEquals("Meeting Room", updatedResource.getName()); // Validate update
	}

	@Test
	public void testDeleteResource() {
		when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

		resourceService.deleteResource(1L);

		verify(resourceRepository, times(1)).delete(resource);
	}
}
