package com.axee.employee.managment.app.service;

import com.axee.employee.managment.app.dto.EmployeeDTO;

public interface EmployeeServiceInterface {
	public EmployeeDTO createEmployee(EmployeeDTO emp);
	public EmployeeDTO getEmployeeById(int id);
	public EmployeeDTO updateEmployee(EmployeeDTO employeeToBeUpdated,int id);
	public String deleteEmployee(int id);
}
