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

import com.example.employeeManagement.dto.DepartmentDto;
import com.example.employeeManagement.service.DepartmentService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController 
{
	private DepartmentService departmentService;
	
	//Add Department REST API
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto)
	{
		DepartmentDto department = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(department, HttpStatus.CREATED);
	}
	
	//Get Department REST API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId)
	{
		DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
		return ResponseEntity.ok(departmentDto);
	}
	
	//Get All Departments REST API
	@GetMapping()
	public ResponseEntity<List<DepartmentDto>> getAllDepartments()
	{
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	//Update Department REST API
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto updatedDepartment)
	{
		DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
		return ResponseEntity.ok(departmentDto);
	}
	
	//Delete Department REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long departmentId)
	{
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department deleted successfully");
	}
	
}
