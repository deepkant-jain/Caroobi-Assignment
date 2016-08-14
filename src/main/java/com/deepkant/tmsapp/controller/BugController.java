package com.deepkant.tmsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deepkant.tmsapp.model.Ticket;
import com.deepkant.tmsapp.model.*;
import com.deepkant.tmsapp.service.*;

@RestController
@RequestMapping("/bug")
public class BugController
{
	@Autowired private TicketService ticketService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Ticket> listAllBugs()
	{
		return this.ticketService.listAll();
	}
	
	@RequestMapping(value = "/unassigned/list", method = RequestMethod.GET)
	public List<Ticket> listUnassignedBugs()
	{
		return this.ticketService.listUnassigned();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Ticket createBug(@RequestBody Ticket argBug)
	{
		return this.ticketService.create(argBug);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Ticket bugInformation(@PathVariable(value = "id") Integer argBugId)
	{
		return this.ticketService.findBy(argBugId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Ticket updateBug(@RequestBody Ticket argBug, @PathVariable(value = "id") Integer argBugId)
	{
		return this.ticketService.update(argBugId, argBug);
	}
	
	@RequestMapping(value = "/status/list", method = RequestMethod.GET)
	public List<TicketStatus> listAllBugStatus()
	{
		return this.ticketService.listStatus();
	}
	
	@RequestMapping(value = "/status/create", method = RequestMethod.POST)
	public TicketStatus createBugStatus(@RequestBody TicketStatus argBugStatus)
	{
		return this.ticketService.createStatus(argBugStatus);
	}
	
	@RequestMapping(value = "/priority/list", method = RequestMethod.GET)
	public List<TicketPriority> listAllBugPriority()
	{
		return this.ticketService.listPriority();
	}
	
	@RequestMapping(value = "/priority/create", method = RequestMethod.POST)
	public TicketPriority createBugPriority(@RequestBody TicketPriority argBugPriority)
	{
		return this.ticketService.createPriority(argBugPriority);
	}
	
	@RequestMapping(value = "/assign/{bugIds}/developer/{developerId}", method = RequestMethod.POST)
	public void assignToDeveloper(@PathVariable(value = "bugIds") List<Integer> argBugIds, 
								  @PathVariable(value = "developerId") Integer argDeveloperId) 
	{
		this.ticketService.assign(argBugIds, argDeveloperId);
	}
}
