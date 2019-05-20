package com.yzl.ssm.test;


import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yzl.ssm.bean.Department;
import com.yzl.ssm.bean.Employee;
import com.yzl.ssm.dao.DepartmentMapper;
import com.yzl.ssm.dao.EmployeeMapper;

/**
 * 测试Mapper 使用springTest单元测试
 * @author Administrator
 * @ContextConfiguration指定spring的配置文件的位置
 * @RunWith指定使用哪个测试模块来进行测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD() {
		System.out.println(departmentMapper);
		//departmentMapper.insertSelective(new Department(null, "研发部"));
		//departmentMapper.insertSelective(new Department(null, "测试部"));
		
		//employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "jerry@163.com", 1));
		
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 500; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
			employeeMapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
		}
		
	}
}
