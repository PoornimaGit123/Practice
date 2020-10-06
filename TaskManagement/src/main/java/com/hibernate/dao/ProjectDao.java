package com.hibernate.dao;

import java.util.List;

import com.hibernate.exception.FetchException;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;

public interface ProjectDao {

	List<Project> findAllProjects() throws FetchException;

	List<Employee> findEmployeesForProject(int projectId) throws FetchException;

	List<Employee> findEmployeesByID(List<String> projectEmployeeList) throws FetchException;

	Project findById(int projectId) throws FetchException;

}
