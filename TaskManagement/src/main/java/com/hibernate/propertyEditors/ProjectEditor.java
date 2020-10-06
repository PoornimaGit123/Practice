package com.hibernate.propertyEditors;

import java.beans.PropertyEditorSupport;

import com.hibernate.form.ProjectVO;

public class ProjectEditor extends PropertyEditorSupport{
	
	@Override
	public void setAsText(String id){
		ProjectVO projectVO;
		switch(Integer.parseInt(id)){
		case 1: projectVO = new ProjectVO(1, "iPhone UI"); break;
		case 2: projectVO = new ProjectVO(2, "iPad Bugs"); break;
		case 3: projectVO = new ProjectVO(3, "iPhone software Development"); break;
		case 4: projectVO = new ProjectVO(4, "iPhone Testing"); break;
		default: projectVO = null;
		}
		this.setValue(projectVO);
	}
	
	
	
}
