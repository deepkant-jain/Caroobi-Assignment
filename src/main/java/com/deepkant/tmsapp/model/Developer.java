package com.deepkant.tmsapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="DEVELOPER")
public class Developer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DEVELOPERID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ACTIVE")
	private boolean active = true;
	
	@OneToMany(targetEntity=Ticket.class, mappedBy="developer", fetch=FetchType.LAZY)
	@JsonManagedReference(value="developer-tickets")
	private List<Ticket> tickets;
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId(Integer argId)
	{
		this.id = argId;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String argName)
	{
		this.name = argName;
	}
	
	public boolean isActive()
	{
		return this.active;
	}
	
	public void setActive(boolean argActive)
	{
		this.active = argActive;
	}
	
	public List<Ticket> getTickets()
	{
		return this.tickets;
	}
	
	public void setBugs(List<Ticket> argTickets)
	{
		this.tickets = argTickets;
	}
	
}
