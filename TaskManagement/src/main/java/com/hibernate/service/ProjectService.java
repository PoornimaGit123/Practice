package com.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.exception.ServiceException;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;


public interface ProjectService {

	List<Project> getAllProjects() throws ServiceException;

	List<Employee> findAllEmployeesForProject(int projectId) throws ServiceException;

	List<Employee> findEmployeesByID(List<String> projectEmployeeList) throws ServiceException;

	Project getById(int projectId) throws ServiceException;

}
