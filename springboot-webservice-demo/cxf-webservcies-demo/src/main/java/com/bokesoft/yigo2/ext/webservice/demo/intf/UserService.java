package com.bokesoft.yigo2.ext.webservice.demo.intf;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.bokesoft.yigo2.ext.webservice.demo.struc.User;


@WebService
public interface UserService {

    @WebMethod
    String getName(@WebParam(name = "userId") String userId);
    @WebMethod
    User getUser(String userId);
    @WebMethod
    public ArrayList<User> getAllUser();
    @WebMethod
    public void dealUserRequest(User user);
}
