package com.bokesoft.yigo2.ext.webservice.demo.intf;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.bokesoft.yigo2.ext.webservice.demo.struc.Department;


@WebService
public interface DepartmentService {
    @WebMethod
    Department getDepartment(String depId);
    @WebMethod
    public ArrayList<Department> getAllDepartment();
}
