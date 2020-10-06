package com.hibernate.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.dao.ProjectDao;
import com.hibernate.dao.TaskDao;
import com.hibernate.exception.FetchException;
import com.hibernate.exception.PersistException;
import com.hibernate.exception.ServiceException;
import com.hibernate.form.TaskForm;
import com.hibernate.models.Employee;
import com.hibernate.models.EmployeeTask;
import com.hibernate.models.Project;
import com.hibernate.models.Task;
import com.hibernate.util.CommonUtil;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TaskDao taskDao;
	
	private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Override
	public Integer saveTask(TaskForm taskForm) throws ServiceException  {
		logger.info("TaskServiceImpl class:: saveTask() method");
		Integer taskId = null;
		try {
			List<Employee> employeesForTheProject = projectService.findEmployeesByID(taskForm.getProjectEmployeeList());
			Project project = projectService.getById(taskForm.getProjectVO().getId());
			Task task = new Task();
			task.setDescription(taskForm.getDescription());
			task.setStartDate(CommonUtil.stringToDate(taskForm.getStartDate()));
			task.setEndDate(CommonUtil.stringToDate(taskForm.getDueDate()));
			task.setProject(project);
			if(null != employeesForTheProject){
				employeesForTheProject.forEach(e->e.getTasks().add(task));
			}
			taskId = taskDao.saveTaskForEmployee(task);
		} catch (ParseException e1) {
			throw new ServiceException();
		}catch(PersistException ex){
			throw new ServiceException();
		}
		return taskId;
	}

	@Override
	public List<Integer> getTaskDetails(int projectId) throws ServiceException {
		logger.info("TaskServiceImpl class:: saveTask() method");
		try {
			return taskDao.findTasksForProject(projectId);
		} catch (FetchException e) {
			throw new ServiceException();
		}
		
	}

	@Override
	public List<EmployeeTask> getEmployeeTaskForTheTaskList(List<Integer> taskList) throws ServiceException {
		logger.info("TaskServiceImpl class:: getEmployeeTaskForTheTaskList() method");
		try {
			return taskDao.findTheEmployeeTaskForTasks(taskList);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Employee findEmployeeById(Integer employeeId) throws ServiceException {
		logger.info("TaskServiceImpl class:: findEmployeeById() method");
		try {
			return taskDao.findById(employeeId);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

	@Override
	public Task getTaskById(Integer t) throws ServiceException {
		logger.info("TaskServiceImpl class:: getTaskById() method");
		try {
			return taskDao.findByTaskId(t);
		} catch (FetchException e) {
			throw new ServiceException();
		}
	}

}
