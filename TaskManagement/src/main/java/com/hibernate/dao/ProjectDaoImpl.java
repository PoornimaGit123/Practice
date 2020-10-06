package com.hibernate.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.exception.DAOException;

import com.hibernate.exception.FetchException;
import com.hibernate.models.Employee;
import com.hibernate.models.Project;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {
	
	private Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Project> findAllProjects() throws FetchException{
		logger.info("ProjectDaoImpl class:: findAllProjects() method");
		try{
		Query query = sessionFactory.getCurrentSession().createQuery("From Project p");
		return query.list();
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
		
	}

	@Override
	public List<Employee> findEmployeesForProject(int projectId) throws FetchException {
		logger.info("ProjectDaoImpl class:: findEmployeesForProject() method");
		try{
		Query query = sessionFactory.getCurrentSession().createQuery("From Employee e where e.project.projectId=:pId");
		query.setInteger("pId", projectId);
		return query.list();
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
	}

	@Override
	public List<Employee> findEmployeesByID(List<String> projectEmployeeList)throws FetchException {
		logger.info("ProjectDaoImpl class:: findEmployeesByID() method");
		List<Integer> employeeIds = projectEmployeeList.stream().map(Integer::parseInt).collect(Collectors.toList());
		StringBuilder query = new StringBuilder("select e from Employee e where e.id in(:employeeIds)");
		try{
			Query q = sessionFactory.getCurrentSession().createQuery(query.toString());
			q.setParameterList("employeeIds", employeeIds);
			return q.list();
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
		
	}

	@Override
	public Project findById(int projectId) throws FetchException{
		logger.info("ProjectDaoImpl class:: findById() method");
		try{
			return (Project) sessionFactory.getCurrentSession().get(Project.class, projectId);
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
	}

}
