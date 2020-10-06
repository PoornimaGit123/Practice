package com.hibernate.form;

import java.util.Date;
import java.util.List;

import com.hibernate.models.Employee;

public class TaskDetailsVO {
	
	private String taskDescription;
	private String taskStartDate;
	private String taskEndDate;
	private List<Employee> employeesList;
	
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	public List<Employee> getEmployeesList() {
		return employeesList;
	}
	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
	}
	public String getTaskStartDate() {
		return taskStartDate;
	}
	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public String getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeesList == null) ? 0 : employeesList.hashCode());
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + ((taskEndDate == null) ? 0 : taskEndDate.hashCode());
		result = prime * result + ((taskStartDate == null) ? 0 : taskStartDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDetailsVO other = (TaskDetailsVO) obj;
		if (employeesList == null) {
			if (other.employeesList != null)
				return false;
		} else if (!employeesList.equals(other.employeesList))
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskEndDate == null) {
			if (other.taskEndDate != null)
				return false;
		} else if (!taskEndDate.equals(other.taskEndDate))
			return false;
		if (taskStartDate == null) {
			if (other.taskStartDate != null)
				return false;
		} else if (!taskStartDate.equals(other.taskStartDate))
			return false;
		return true;
	}
	 
	
	

}
