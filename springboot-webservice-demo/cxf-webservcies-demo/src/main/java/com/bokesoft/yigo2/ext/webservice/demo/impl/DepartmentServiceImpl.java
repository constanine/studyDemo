package com.bokesoft.yigo2.ext.webservice.demo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.bokesoft.yigo2.ext.webservice.demo.intf.DepartmentService;
import com.bokesoft.yigo2.ext.webservice.demo.struc.Department;

@WebService(targetNamespace="http://localhost:8080/",endpointInterface = "com.bokesoft.yigo2.ext.webservice.demo.intf.DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
	private Map<String, Department> depMap = new HashMap<String, Department>();

	public DepartmentServiceImpl() {
		System.out.println("向实体类插入数据");
		Department dep1 = new Department();
		dep1.setDepId("1001");
		dep1.setDepname("boke");
		depMap.put(dep1.getDepId(), dep1);

		Department dep2 = new Department();
		dep2.setDepId("1002");
		dep2.setDepname("estore");
		depMap.put(dep2.getDepId(), dep2);

		Department dep3 = new Department();
		dep3.setDepId("1003");
		dep3.setDepname("erp");
		depMap.put(dep3.getDepId(), dep3);
	}

	@Override
	public Department getDepartment(String depId) {
		
		return depMap.get(depId);
	}

	@Override
	public ArrayList<Department> getAllDepartment() {
		ArrayList<Department> deps = new ArrayList<>();
		depMap.forEach((key, value) -> {
			deps.add(value);
		});
		return deps;
	}

}
