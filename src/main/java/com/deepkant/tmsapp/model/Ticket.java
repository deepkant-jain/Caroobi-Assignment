package com.deepkant.tmsapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="BUG")
public class Ticket
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ISSUEID")
	private Integer issueId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATIONDATE")
	private Date creationDate;
	
	@OneToOne
	@JoinColumn(name="BUGSTATUSID")
	private TicketStatus status;
	
	@OneToOne
	@JoinColumn(name="BUGPRIORITYID")
	private TicketPriority priority;
	
	@ManyToOne
	@JoinColumn(name="DEVELOPERID")
	@JsonBackReference(value="developer-bugs")
	private Developer developer;
	
	public Integer getIssueId()
	{
		return this.issueId;
	}
	
	public void setIssueId(Integer argIssueId)
	{
		this.issueId = argIssueId;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String argTitle)
	{
		this.title = argTitle;
	}
	
	public Date getCreationDate()
	{
		return this.creationDate;
	}
	
	public void setCreationDate(Date argCreationDate)
	{
		this.creationDate = argCreationDate;
	}
	
	public TicketStatus getStatus()
	{
		return this.status;
	}
	
	public void setStatus(TicketStatus argStatus)
	{
		this.status = argStatus;
	}
	
	public TicketPriority getPriority()
	{
		return this.priority;
	}
	
	public void setPriority(TicketPriority argPriority)
	{
		this.priority = argPriority;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String argDescription)
	{
		this.description = argDescription;
	}
	
	public Developer getDeveloper()
	{
		return this.developer;
	}
	
	public void setDeveloper(Developer argDeveloper)
	{
		this.developer = argDeveloper;
	}
}
