package com.axee.employee.managment.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axee.employee.managment.app.dto.EmployeeDTO;
import com.axee.employee.managment.app.model.Employee;
import com.axee.employee.managment.app.model.EmployeeRequest;
import com.axee.employee.managment.app.model.EmployeeResponse;
import com.axee.employee.managment.app.repository.EmployeeRepository;
import com.axee.employee.managment.app.service.EmployeeServiceInterface;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	//Create Employee REST API
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest e){
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		EmployeeDTO inputDTO = mapper.map(e, EmployeeDTO.class);
		
		EmployeeDTO outputDTO = employeeServiceInterface.createEmployee(inputDTO);
		
		EmployeeResponse outputResponse=mapper.map(outputDTO, EmployeeResponse.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(outputResponse);
		
	}
	
	//Get All Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")	
	public ResponseEntity<?> getEmployeeById(@PathVariable int id){
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		EmployeeDTO outputEmployeeDTO=employeeServiceInterface.getEmployeeById(id);
		
		EmployeeResponse outputResponse=mapper.map(outputEmployeeDTO, EmployeeResponse.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(outputResponse);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employeeToBeUpdated ,@PathVariable int id){
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		EmployeeDTO employeeDTOToBeUpdated=mapper.map(employeeToBeUpdated, EmployeeDTO.class);
		
		EmployeeDTO updatedDTOEmployee=employeeServiceInterface.updateEmployee(employeeDTOToBeUpdated, id);
		
		EmployeeResponse outputResponse=mapper.map(updatedDTOEmployee, EmployeeResponse.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(outputResponse);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmploye(@PathVariable int id){
		String outputResponseStr=employeeServiceInterface.deleteEmployee(id);
		Map<String, String> map=new HashMap<>();
		map.put("Delete", outputResponseStr);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
}
