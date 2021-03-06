package com.example.BikeRentalSystem.controller;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.BikeRentalSystem.entities.Vehicle;
import com.example.BikeRentalSystem.service.VehicleService;



@RestController
public class VehicleRestController {
	@Autowired
	private VehicleService vehicleService;
	

	@GetMapping("vehicles")
	public ResponseEntity<List<Vehicle>> getAllVehicles()
	{
		List<Vehicle> vehicles=vehicleService.getAllVehicles();
		if(vehicles.size()==0){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else{
			return ResponseEntity.ok(vehicles);
		}
	}
	
	@GetMapping("vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") String id)
	{
		Optional<Vehicle> vehicle=vehicleService.getVehicleById(id);
		if(vehicle.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else{
			return ResponseEntity.ok(vehicle.get());
		}
	}
	
	@PostMapping("vehicles")
	public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {

		Vehicle addedVehicle= vehicleService.addVehicle(vehicle);
		return ResponseEntity.ok(addedVehicle);
	}
	
	@DeleteMapping("vehicles/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") String id) {
		vehicleService.deleteVehicle(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("vehicles/{id}")
	public ResponseEntity<Vehicle> updateStation(@RequestBody Vehicle vehicle, @PathVariable("id") String id) {
		Vehicle updatedVehicle= vehicleService.updateVehicle(vehicle,id);
		return ResponseEntity.ok(updatedVehicle);
	}
}
