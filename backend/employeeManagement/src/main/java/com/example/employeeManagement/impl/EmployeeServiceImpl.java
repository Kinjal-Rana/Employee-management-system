package com.example.employeeManagement.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.employeeManagement.dto.EmployeeDto;
import com.example.employeeManagement.entity.Department;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.exception.ResourceNotFoundException;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.repository.DepartmentRepository;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.EmployeeService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRespository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) 
	{
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		
		Department department = departmentRespository.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException
						("Department is not exists with id : "+employeeDto.getDepartmentId()));
		
		employee.setDepartment(department);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) 
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow( () -> new ResourceNotFoundException("Employee is not exists with given Id : "+employeeId));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() 
	{
		List<Employee> employees = employeeRepository.findAll();
		
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) 
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow( () -> new ResourceNotFoundException("Employee is not exists with given Id : "+employeeId));
		 
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmailId(updatedEmployee.getEmailId());
		
		Department department = departmentRespository.findById(updatedEmployee.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException
						("Department is not exists with id : "+updatedEmployee.getDepartmentId()));
		
		employee.setDepartment(department);
		
		Employee updatedEmployeeObj = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) 
	{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow( () -> new ResourceNotFoundException("Employee is not exists with given Id : "+employeeId));
		
		employeeRepository.deleteById(employeeId);
		
	}

}
