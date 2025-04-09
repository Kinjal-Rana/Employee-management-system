package com.example.employeeManagement.service;

import java.util.List;

import com.example.employeeManagement.dto.DepartmentDto;

public interface DepartmentService 
{
	DepartmentDto createDepartment(DepartmentDto departmentDto);
	
	DepartmentDto getDepartmentById(Long employeeId);
	
	List<DepartmentDto> getAllDepartments();
	
	DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);
	
	void deleteDepartment(Long departmentId);
}
