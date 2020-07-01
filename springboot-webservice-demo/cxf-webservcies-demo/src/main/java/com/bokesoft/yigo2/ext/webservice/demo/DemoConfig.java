package com.bokesoft.yigo2.ext.webservice.demo;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bokesoft.yigo2.ext.webservice.demo.impl.DepartmentServiceImpl;
import com.bokesoft.yigo2.ext.webservice.demo.impl.UserServiceImpl;
import com.bokesoft.yigo2.ext.webservice.demo.impl.pushDataToEJGSImpl;
import com.bokesoft.yigo2.ext.webservice.demo.intf.DepartmentService;
import com.bokesoft.yigo2.ext.webservice.demo.intf.UserService;
import com.bokesoft.yigo2.ext.webservice.demo.intf.pushDataToEJGS;

@Configuration
public class DemoConfig {
	

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	
	@Bean(name="webservice")
	public ServletRegistrationBean<CXFServlet> dispatchUserService() {
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(), "/webservice/*");// 发布服务名称
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public DepartmentService depService() {
		return new DepartmentServiceImpl();
	}

	@Bean
	public pushDataToEJGS pushDataToEJGS() {
		return new pushDataToEJGSImpl();
	}
	
	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userService());// 绑定要发布的服务
		endpoint.publish("/user"); // 显示要发布的名称
		return endpoint;
	}
	
	@Bean
	public Endpoint depEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), depService());// 绑定要发布的服务
		endpoint.publish("/dep"); // 显示要发布的名称
		return endpoint;
	}
	
	@Bean
	public Endpoint FZBEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), pushDataToEJGS());// 绑定要发布的服务
		endpoint.publish("/pushDataToEJGS"); // 显示要发布的名称
		return endpoint;
	}
}
