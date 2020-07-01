package com.bokesoft.yigo2.ext.webservice.demo.struc;

import java.io.Serializable;

public class Department implements Serializable {
	private static final long serialVersionUID = -5939599210753662529L;
	private String depId;
	private String depname;

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
