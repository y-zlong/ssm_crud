package com.yzl.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.ssm.bean.Department;
import com.yzl.ssm.bean.Msg;
import com.yzl.ssm.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDept() {
		List<Department> list = departmentService.getDept();
		return Msg.success().add("depts", list);
	}
}
