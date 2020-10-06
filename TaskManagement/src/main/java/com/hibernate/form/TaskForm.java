package com.hibernate.form;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.hibernate.models.Project;

public class TaskForm {
	
	@NotNull(message="Project should be selected")
	private ProjectVO projectVO;
	@NotEmpty(message="Description is required")
	private String description;
	@NotEmpty(message="Start Date cannot be empty")
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private String startDate;
	@NotEmpty(message="Due date cannot be empty")
	@DateTimeFormat(pattern="dd-mm-yyyy")
	private String dueDate;
	@NotNull(message="Employees' must be selected")
	private List<String> projectEmployeeList;
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getProjectEmployeeList() {
		return projectEmployeeList;
	}
	public void setProjectEmployeeList(List<String> projectEmployeeList) {
		this.projectEmployeeList = projectEmployeeList;
	}
	
	public TaskForm() {
		super();
	}
	/*public Map<Integer,String> getProjectMap() {
		return projectMap;
	}
	public void setProjectMap(Map<Integer,String> projectMap) {
		this.projectMap = projectMap;
	}*/
	
	@Override
	public String toString() {
		return "TaskForm [description=" + description + ", startDate=" + startDate
				+ ", dueDate=" + dueDate + ", projectEmployeeList=" + projectEmployeeList + "]";
	}

	

	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}

	
	
	
	
	
	
	

}
