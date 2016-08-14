package com.deepkant.tmsapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUG_PRIORITY")
public class TicketPriority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BUGPRIORITYID")
	private Integer id;
	
	@Column(name="DESCRIPTION")
	private String description;

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer argId)
	{
		this.id = argId;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String argDescription)
	{
		this.description = argDescription;
	}
}
