package com.yzl.ssm.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.yzl.ssm.bean.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:spring.xml","classpath:springmvc.xml"})
public class MVCtest {
	@Autowired
	WebApplicationContext context;
	
	
	MockMvc mockMvc;
	
	@Before
	public void initMock() {
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		 MockHttpServletRequest request = result.getRequest();
		PageInfo<Employee> pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码"+pi.getPageNum());
		System.out.println("总页数： "+pi.getPages());
		System.out.println("总记录数： "+pi.getTotal());
		System.out.println("页面中连续显示的页码数");
		int[] nums = pi.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(i);
		}
		List<Employee> list = pi.getList();
		for (Employee employee : list) {
			System.out.println("ID "+employee.getEmpId() +"NAME "+employee.getEmpName());
		}
	}

}
