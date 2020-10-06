package com.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="EMPLOYEE_TASK")
@Entity
public final class EmployeeTask {
	
	@EmbeddedId
	private EmployeeTaskId employeeTaskId; 

	public EmployeeTask() {
		super();
	}

	public EmployeeTaskId getEmployeeTaskId() {
		return employeeTaskId;
	}

	public void setEmployeeTaskId(EmployeeTaskId employeeTaskId) {
		this.employeeTaskId = employeeTaskId;
	}
	
	

}
