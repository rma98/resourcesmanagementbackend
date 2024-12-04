package br.edu.ifpe.manager.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ROOM")
public class Room extends Resource {

	Room(Long id, String name, String description, int capacity, ResourceStatus status, String location,
			List<Reservation> reservations) {
		super(id, name, description, capacity, status, location, reservations);
		// TODO Auto-generated constructor stub
	}
    // Room specific behaviors can be added here if necessary.
}
