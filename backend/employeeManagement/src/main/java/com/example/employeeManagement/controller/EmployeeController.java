package com.example.employeeManagement.controller;

import java.util.List;

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

import com.example.employeeManagement.dto.EmployeeDto;
import com.example.employeeManagement.service.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin("*") //* means all the clients can able to call the employee related APIs
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController 
{
	private EmployeeService employeeService;
	
	//Add Employee REST API
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	//Get Employee REST API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId)
	{
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employeeDto);
	}
	
	//Get all Employees REST API
	@GetMapping()
	public ResponseEntity<List<EmployeeDto>> getAllEmployees()
	{
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	//Update Employee REST API
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee)
	{
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
		return ResponseEntity.ok(employeeDto);
	}
	
	//Delete Employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deleted successfully");
	}
}
