package com.hibernate.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.dao.ProjectDao;
import com.hibernate.dao.ProjectDaoImpl;
import com.hibernate.exception.FetchException;
import com.hibernate.exception.ServiceException;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	ProjectDao projectDao;

	@Override
	public List<Project> getAllProjects() throws ServiceException {
		logger.info("ProjectServiceImpl class:: getAllProjects() method");
		try {
			return projectDao.findAllProjects();
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Employee> findAllEmployeesForProject(int projectId) throws ServiceException {
		logger.info("ProjectServiceImpl class:: findAllEmployeesForProject() method");
		try {
			return projectDao.findEmployeesForProject(projectId);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<Employee> findEmployeesByID(List<String> projectEmployeeList) throws ServiceException {
		logger.info("ProjectServiceImpl class:: findEmployeesByID() method");
		try {
			return projectDao.findEmployeesByID(projectEmployeeList);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Project getById(int projectId) throws ServiceException {
		logger.info("ProjectServiceImpl class:: getById() method");
		try {
			return projectDao.findById(projectId);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

}
