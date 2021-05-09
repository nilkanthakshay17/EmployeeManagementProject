package com.axee.employee.managment.app.service;

import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.axee.employee.managment.app.dto.EmployeeDTO;
import com.axee.employee.managment.app.exception.ResourceNotFoundException;
import com.axee.employee.managment.app.model.Employee;
import com.axee.employee.managment.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeServiceInterface {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeInputDTO) {
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Employee employeeInputEntity=mapper.map(employeeInputDTO, Employee.class);
		
		Employee employeeOutputEntity=employeeRepository.save(employeeInputEntity);
		
		return mapper.map(employeeOutputEntity, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO getEmployeeById(int id) {
		Employee outputEmployeeEntity=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with Id: "+id+" doesn't exist","Resource Not Found"));
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return mapper.map(outputEmployeeEntity, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeToBeUpdated,int id) {
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		EmployeeDTO employeeTemp=mapper.map(employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with Id: "+id+" doesn't exist","Resource Not Found")),EmployeeDTO.class);
		
		employeeTemp.setFirstName(employeeToBeUpdated.getFirstName());
		employeeTemp.setLastName(employeeToBeUpdated.getLastName());
		employeeTemp.setEmailId(employeeToBeUpdated.getEmailId());
		
		Employee returnedUpdatedEmployee=employeeRepository.save(mapper.map(employeeTemp,Employee.class));
		
		EmployeeDTO employeeDTOReturnValue=mapper.map(returnedUpdatedEmployee, EmployeeDTO.class);
		
		return employeeDTOReturnValue;
	}

	@Override
	public String deleteEmployee(int id) {
		
		Employee emp=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee with Id: "+id+" doesn't exist","Resource Not Found"));
		
		employeeRepository.deleteById(id);
		
		return "SUCCESS";
	}

}
