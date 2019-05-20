package com.yzl.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.ssm.bean.Employee;
import com.yzl.ssm.dao.EmployeeMapper;
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;

	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
		
	}

	public void empSave(Employee employee) {
		employeeMapper.insertSelective(employee);
		
	}

}
