package com.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.hibernate.exception.FetchException;
import com.hibernate.exception.PersistException;
import com.hibernate.models.Employee;
import com.hibernate.models.EmployeeTask;
import com.hibernate.models.Task;

@Repository
public class TaskDaoImpl implements TaskDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);

	@Override
	public Integer saveTaskForEmployee(Task task) throws PersistException {
		logger.info("TaskDaoImpl class:: saveTaskForEmployee() method");
		try{
			return (Integer) sessionFactory.getCurrentSession().save(task);
		}catch(DataAccessException e){
			throw new PersistException(e.getMessage());
		}
	
	}

	@Override
	public List<Integer> findTasksForProject(int projectId) throws FetchException {
		logger.info("TaskDaoImpl class:: findTasksForProject() method");
		StringBuilder q = new StringBuilder("select t.taskId from Task t where t.project.projectId=:pId");
		try{
		Query query = sessionFactory.getCurrentSession().createQuery(q.toString());
		query.setInteger("pId",projectId);
		return query.list();
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
	}

	@Override
	public List<EmployeeTask> findTheEmployeeTaskForTasks(List<Integer> taskList) throws FetchException {
		logger.info("TaskDaoImpl class:: findTheEmployeeTaskForTasks() method");
		StringBuilder q = new StringBuilder("from EmployeeTask et where et.employeeTaskId.taskId in (:taskIds)");
		try{
		Query query = sessionFactory.getCurrentSession().createQuery(q.toString());
		query.setParameterList("taskIds", taskList);
		return query.list();
		}catch(DataAccessException e){
			throw new FetchException(e.getMessage());
		}
	}

	@Override
	public Employee findById(Integer employeeId) throws FetchException {
		logger.info("TaskDaoImpl class:: findById() method");
		try{
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		}catch(DataAccessException e){
			throw new FetchException();
		}
	}

	@Override
	public Task findByTaskId(Integer t) throws FetchException {
		logger.info("TaskDaoImpl class:: findByTaskId() method");
		try{
		return (Task) sessionFactory.getCurrentSession().get(Task.class, t);
		}catch(DataAccessException e){
			throw new FetchException();
		}
	}
	
	

}
