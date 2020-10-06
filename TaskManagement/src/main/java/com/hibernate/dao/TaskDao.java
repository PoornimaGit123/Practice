package com.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hibernate.exception.FetchException;
import com.hibernate.exception.PersistException;
import com.hibernate.exception.ServiceException;
import com.hibernate.models.Employee;
import com.hibernate.models.EmployeeTask;
import com.hibernate.models.Task;


public interface TaskDao {

	Integer saveTaskForEmployee(Task task) throws PersistException;

	List<Integer> findTasksForProject(int projectId) throws ServiceException, FetchException;

	List<EmployeeTask> findTheEmployeeTaskForTasks(List<Integer> taskList) throws FetchException;

	Employee findById(Integer employeeId) throws FetchException;

	Task findByTaskId(Integer t) throws FetchException;

}
