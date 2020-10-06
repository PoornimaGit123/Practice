package com.hibernate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibernate.exception.ServiceException;
import com.hibernate.form.ProjectVO;
import com.hibernate.form.TaskDetailsVO;
import com.hibernate.form.TaskForm;
import com.hibernate.models.Employee;
import com.hibernate.models.EmployeeTask;
import com.hibernate.models.Project;
import com.hibernate.models.Task;
import com.hibernate.propertyEditors.ProjectEditor;
import com.hibernate.service.ProjectService;
import com.hibernate.service.ProjectServiceImpl;
import com.hibernate.service.TaskService;
import com.hibernate.util.CommonUtil;

@Controller
public class TaskController {
	
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskService taskService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));	
		binder.registerCustomEditor(ProjectVO.class, new ProjectEditor());
	}
	
	@ModelAttribute("projectList")
	public List<ProjectVO> populateTheProjects(Model model){
		logger.info("TaskController class:: populateTheProjects() method");
		List<ProjectVO> projectList = null;
		try{
		List<Project> projects = projectService.getAllProjects();
		projectList = projects.stream()
			                .map(p -> new ProjectVO(p.getProjectId(),p.getName()))
			                .collect(Collectors.toList());
		
		}catch(ServiceException e){
			model.addAttribute("errorMsg", "Something went wrong in the application.Please try Later");
		}
		return projectList;
		}
	
	@RequestMapping(value="/getTaskPage.action",method=RequestMethod.GET)
	public String viewTaskPage(Model model){
		logger.info("TaskController class:: viewTaskPage() method"); 
		try{
		TaskForm taskForm = new TaskForm();
		List<Project> projects = projectService.getAllProjects(); 
		Map<Integer,String> projectMap  = projects.stream().collect(Collectors.toMap(Project::getProjectId,Project::getName));
		model.addAttribute("taskForm",taskForm);
		}catch(ServiceException e){
			model.addAttribute("errorMsg", "Something went wrong in the application.Please try Later");
		}
		return "addTask";
	}
	
	@RequestMapping(value="getEmployeeListForProject.action",method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> getEmployeesList(Model model,@RequestParam("projectId")String projectId){
		logger.info("TaskController class:: getEmployeesList() method");
		List<Employee> employeesList = null;
		try{
			employeesList = projectService.findAllEmployeesForProject(Integer.parseInt(projectId)); 
		}catch(ServiceException e){
			model.addAttribute("errorMsg", "Something went wrong in the application.Please try Later");
		}
		return employeesList;
	}
	
	@RequestMapping(value="saveTask.action",method=RequestMethod.POST)
	public ModelAndView saveTask(@Valid @ModelAttribute TaskForm taskForm,BindingResult result,Model model,RedirectAttributes rdirAttribs){
		logger.info("TaskController class:: saveTask() method");
		logger.debug("TaskController class:: projectEmployee List"+taskForm.getProjectEmployeeList());
		ModelAndView mav = new ModelAndView();
		try{
		if(result.hasErrors()){
			mav.setViewName("addTask");
		}else{
		Integer taskId = taskService.saveTask(taskForm);
		if (null != taskId){
			RedirectView view = new RedirectView("/TaskManagement/");
			view.setExposeModelAttributes(true);
			mav.setView(view);
			mav.addObject("successMsg", "Task added successfully.Task Id :"+taskId);
			logger.debug("Task has been saved successfully");
			}
		}}catch(ServiceException e){
			mav.addObject("errorMsg", "Something went wrong in the application.Please try Later");
		}
		return mav;
	}
	
	@RequestMapping(value="/viewTasks.action",method=RequestMethod.GET)
	public String viewTasks(Model model){
		logger.info("TaskController class:: viewTasks() method");
		return "viewTasks";
		
	}
	
	@RequestMapping(value="getTaskDetailsForProject.action",method=RequestMethod.GET)
	@ResponseBody
	public String getTaskDetails(@RequestParam("projectId") String projectId, Model model) throws JsonProcessingException{
		logger.info("TaskController class:: getTaskDetails() method");
		logger.debug("Project ID:::"+Integer.parseInt(projectId));
		List<TaskDetailsVO> taskDetailsVO = new ArrayList<>();
		Map<String,TaskDetailsVO> viewMap = new HashMap<>();
		String jsonFromMap = null;
		try{
		List<Integer> taskList = taskService.getTaskDetails(Integer.parseInt(projectId));
		List<EmployeeTask> employeeTaskList = taskService.getEmployeeTaskForTheTaskList(taskList); 
		TaskDetailsVO taskDetails = null;
		for(Integer t:taskList){
			List<Employee> employeeFortask = new ArrayList<>();
			taskDetails = new TaskDetailsVO();
			for(EmployeeTask et : employeeTaskList){
				if(t.equals(et.getEmployeeTaskId().getTaskId())){
					Employee emp = taskService.findEmployeeById(et.getEmployeeTaskId().getEmployeeId());
					employeeFortask.add(emp);
				}
			}
			Task task = taskService.getTaskById(t);
			taskDetails.setTaskDescription(task.getDescription());
			taskDetails.setTaskStartDate(CommonUtil.dateToString(task.getStartDate()));
			taskDetails.setTaskEndDate(CommonUtil.dateToString(task.getEndDate()));
			taskDetails.setEmployeesList(employeeFortask);
			
		}
		Project project = projectService.getById(Integer.parseInt(projectId));
		//ProjectVO projectVO = new ProjectVO(project.getProjectId(),project.getName());
		viewMap.put(project.getName(), taskDetails);
		ObjectMapper mapper = new ObjectMapper();
		jsonFromMap = mapper.writeValueAsString(viewMap);
		}catch(ServiceException e){
			model.addAttribute("errorMsg", "Something went wrong in the application.Please try Later");
		}
		logger.debug("JSON String map returned"+jsonFromMap);
		return jsonFromMap;
	}
	
	@RequestMapping(value="getTaskDetailsForAllTheProjects.action",method=RequestMethod.GET)
	@ResponseBody
	public String getTaskDetailsForAllProjects(@RequestParam("projectId") String projectId,Model model) throws JsonProcessingException{
		logger.debug("get All Projects task details:::"+Integer.parseInt(projectId));
		logger.info("TaskController class:: getTaskDetailsForAllProjects() method");
		List<TaskDetailsVO> taskDetailsVO = new ArrayList<>();
		Map<String,TaskDetailsVO> viewMap = new HashMap<>();
		String jsonFromMap = null;
		try{
		List<Project> allProjects = projectService.getAllProjects();
		for(Project p:allProjects){
		List<Integer> taskList = taskService.getTaskDetails(p.getProjectId());
		//List<Employee> empList = projectService.findAllEmployeesForProject(Integer.parseInt(projectId));
		List<EmployeeTask> employeeTaskList = taskService.getEmployeeTaskForTheTaskList(taskList); 
		TaskDetailsVO taskDetails = null;
		for(Integer t:taskList){
			List<Employee> employeeFortask = new ArrayList<>();
			taskDetails = new TaskDetailsVO();
			for(EmployeeTask et : employeeTaskList){
				if(t.equals(et.getEmployeeTaskId().getTaskId())){
					Employee emp = taskService.findEmployeeById(et.getEmployeeTaskId().getEmployeeId());
					employeeFortask.add(emp);
				}
			}
			Task task = taskService.getTaskById(t);
			taskDetails.setTaskDescription(task.getDescription());
			taskDetails.setTaskStartDate(CommonUtil.dateToString(task.getStartDate()));
			taskDetails.setTaskEndDate(CommonUtil.dateToString(task.getEndDate()));
			taskDetails.setEmployeesList(employeeFortask);
			
		}
		viewMap.put(p.getName(), taskDetails);
		}
		ObjectMapper mapper = new ObjectMapper();
		jsonFromMap = mapper.writeValueAsString(viewMap);
		}catch(ServiceException e){
			model.addAttribute("errorMsg", "Something went wrong in the application.Please try Later");
		}
		logger.debug("JSON string for all projects task details::"+jsonFromMap);
		return jsonFromMap;
	}
	
	
}
