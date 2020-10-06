package com.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.exception.ServiceException;
import com.hibernate.form.TaskForm;
import com.hibernate.models.Employee;
import com.hibernate.models.EmployeeTask;
import com.hibernate.models.Task;


public interface TaskService {

	Integer saveTask(TaskForm taskForm) throws ServiceException;

	List<Integer> getTaskDetails(int parseInt) throws ServiceException;

	List<EmployeeTask> getEmployeeTaskForTheTaskList(List<Integer> taskList) throws ServiceException;

	Employee findEmployeeById(Integer employeeId) throws ServiceException;

	Task getTaskById(Integer t) throws ServiceException;

}
