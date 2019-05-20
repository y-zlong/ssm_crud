package com.yzl.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.ssm.bean.Employee;
import com.yzl.ssm.bean.Msg;
import com.yzl.ssm.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> info = new PageInfo<Employee>(emps,5);
		return Msg.success().add("pageInfo", info);
		
		
	}
	
	@RequestMapping(value="/emp" ,method=RequestMethod.POST)
	@ResponseBody
	public Msg empSave(Employee employee) {
		employeeService.empSave(employee);
		return Msg.success();
	}
	/*
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model ) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAll();
		PageInfo<Employee> info = new PageInfo<Employee>(emps,5);
		model.addAttribute("pageInfo", info);
		
		return "list";
	}*/
}
