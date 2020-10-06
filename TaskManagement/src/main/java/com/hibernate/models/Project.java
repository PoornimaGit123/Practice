package com.hibernate.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="PROJECT")
@Entity
public class Project implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PROJECT_ID")
	private Integer projectId;
	
	@Column(name="NAME")
	private String name;
	
	/*@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="project")
	private Set<Employee> employees = new HashSet<>();*/

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Project() {
		super();
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + "]";
	}

	
	
	
	
	

}
