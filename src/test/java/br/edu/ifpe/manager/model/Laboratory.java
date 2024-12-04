package br.edu.ifpe.manager.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LAB")
public class Laboratory extends Resource {

	Laboratory(Long id, String name, String description, int capacity, ResourceStatus status, String location,
			List<Reservation> reservations) {
		super(id, name, description, capacity, status, location, reservations);
		// TODO Auto-generated constructor stub
	}
    // Laboratory specific behaviors can be added here if necessary.
}
