package com.yzl.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.ssm.bean.Department;
import com.yzl.ssm.dao.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDept() {
		// TODO Auto-generated method stub
		return departmentMapper.selectByExample(null);
	}

	
}
