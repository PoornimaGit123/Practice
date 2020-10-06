package com.hibernate.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.util.Date;

import com.hibernate.form.TaskForm;
import com.hibernate.util.CommonUtil;

public class TaskValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return TaskForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TaskForm taskForm = (TaskForm) target;
		Date startDate;
		try {
			startDate = CommonUtil.stringToDate(taskForm.getStartDate());
			Date dueDate = CommonUtil.stringToDate(taskForm.getDueDate());
			if(dueDate.before(startDate)){
				errors.rejectValue("dueDate", "Due Date cannot be before the Start date");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
