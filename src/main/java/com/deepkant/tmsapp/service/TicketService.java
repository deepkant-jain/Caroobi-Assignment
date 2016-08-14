package com.deepkant.tmsapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepkant.tmsapp.model.Ticket;
import com.deepkant.tmsapp.model.TicketPriority;
import com.deepkant.tmsapp.model.TicketStatus;
import com.deepkant.tmsapp.model.Developer;
import com.deepkant.tmsapp.repository.TicketPriorityRepository;
import com.deepkant.tmsapp.repository.TicketRepository;
import com.deepkant.tmsapp.repository.TicketStatusRepository;

@Service
@Transactional
public class TicketService
{
	@Autowired private TicketRepository bugRepo;
	@Autowired private TicketStatusRepository bugStatusRepo;
	@Autowired private TicketPriorityRepository bugPriorityRepo;
	@Autowired private DeveloperService userService;
	
	public List<Ticket> listAll()
	{
		return this.bugRepo.findAll();
	}

	public List<Ticket> listUnassigned()
	{
		return this.bugRepo.findUnassigned();
	}

	public Ticket create(Ticket argBug)
	{
		this.setStatus(argBug);
		this.setPriority(argBug);
		
		argBug.setIssueId(null);
		argBug.setCreationDate(Calendar.getInstance().getTime());
		
		Ticket newBug = this.bugRepo.saveAndFlush(argBug);
		if(argBug.getDeveloper() != null && argBug.getDeveloper().getId() != null) {
			Developer developer = this.userService.findBy(argBug.getDeveloper().getId());
			newBug.setDeveloper(developer);
			this.assign(Arrays.asList(newBug.getIssueId()), developer.getId());
		}
		
		return newBug;
	}

	private void setDeveloper(Ticket argBug)
	{
		if(argBug.getDeveloper() != null && argBug.getDeveloper().getId() != null) {
			Developer user = this.userService.findBy(argBug.getDeveloper().getId());
			argBug.setDeveloper(user);
		}
	}

	@SuppressWarnings("unused")
	private void assignToDeveloper(Developer argDeveloper, Ticket argBug)
	{
		if(argDeveloper != null) {
			this.assign(Arrays.asList(argBug.getIssueId()), argDeveloper.getId());			
		}
	}
	
	private void setStatus(Ticket argBug)
	{
		if(argBug.getStatus() == null) {
			throw new IllegalArgumentException("Choose correct status!");
		}
		
		TicketStatus status = this.bugStatusRepo.findOne(argBug.getStatus().getId());
		if(status == null) {
			throw new IllegalArgumentException("Choose correct status!");
		}
		argBug.setStatus(status);
	}

	private void setPriority(Ticket argBug)
	{
		if(argBug.getPriority() == null) {
			throw new IllegalArgumentException("Choose correct priority!");
		}
		
		TicketPriority priority = this.bugPriorityRepo.findOne(argBug.getPriority().getId());
		if(priority == null) {
			throw new IllegalArgumentException("Choose correct priority!");
		}
		argBug.setPriority(priority);
	}

	public Ticket findBy(Integer argBugId)
	{
		return this.bugRepo.findOne(argBugId);
	}

	public Ticket update(Integer argBugId, Ticket argBug)
	{
		Ticket ticket = this.bugRepo.findOne(argBugId);
		if(ticket == null) {
			throw new IllegalArgumentException("Choose correct bug!");			
		}
		ticket.setIssueId(argBugId);
		ticket.setStatus(argBug.getStatus());
		ticket.setPriority(argBug.getPriority());
		ticket.setDeveloper(argBug.getDeveloper());
		ticket.setDescription(argBug.getDescription());
		ticket.setTitle(argBug.getTitle());
		
		this.setStatus(ticket);
		this.setPriority(ticket);
		this.setDeveloper(ticket);

		return this.bugRepo.saveAndFlush(ticket);
	}

	public List<TicketStatus> listStatus()
	{
		return this.bugStatusRepo.findAll();
	}

	public TicketStatus createStatus(TicketStatus argBugStatus)
	{
		argBugStatus.setId(null);
		return this.bugStatusRepo.saveAndFlush(argBugStatus);
	}

	public List<TicketPriority> listPriority()
	{
		return this.bugPriorityRepo.findAll();
	}

	public TicketPriority createPriority(TicketPriority argBugPriority)
	{
		argBugPriority.setId(null);
		return this.bugPriorityRepo.saveAndFlush(argBugPriority);
	}

	public void assign(List<Integer> argBugIdList, Integer argDeveloperId)
	{
		Developer developer = this.userService.findBy(argDeveloperId);
		if(developer == null) {
			throw new IllegalArgumentException("Choose correct developer!");			
		}
		
		List<Ticket> bugListToAssign = new ArrayList<>();
		
		for(Integer itemId: argBugIdList ){
			Ticket ticket = this.findBy(itemId);
			if(ticket == null) {
				throw new IllegalArgumentException("Choose correct bugs!");	
			}
			ticket.setDeveloper(developer);
			this.update(ticket.getIssueId(), ticket);
			bugListToAssign.add(ticket);
		}
			
		developer.setBugs(bugListToAssign);
		this.userService.update(argDeveloperId, developer);
	}

	public List<Ticket> getBugsBy(Integer argDeveloperId)
	{
		return this.bugRepo.getBugsBy(argDeveloperId);
	}
}
