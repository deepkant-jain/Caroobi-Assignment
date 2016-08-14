package com.deepkant.tmsapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepkant.tmsapp.model.Ticket;
import com.deepkant.tmsapp.model.Developer;
import com.deepkant.tmsapp.repository.DeveloperRepository;

@Service
@Transactional
public class DeveloperService
{
	@Autowired private DeveloperRepository developerRepo;
	@Autowired private TicketService ticketService;

	public List<Developer> findAll()
	{
		return this.developerRepo.findAll();
	}

	public Developer create(Developer argDeveloper)
	{
		if(developerExist(argDeveloper)) {
			throw new RuntimeException("Developer Exists"); 
		}
		
		argDeveloper.setId(null);
		argDeveloper.setActive(true);
		return this.developerRepo.saveAndFlush(argDeveloper);
	}

	private boolean developerExist(Developer argDeveloper)
	{
		return this.developerRepo.findByName(argDeveloper.getName()) != null ? true : false;
	}

	public Developer findBy(Integer argDeveloperId)
	{
		if(argDeveloperId == null) {
			throw new RuntimeException("Choose correct developer!");
		}
		return this.developerRepo.findOne(argDeveloperId);
	}

	public void delete(Integer argDeveloperId)
	{
		Developer developer = checkAndReturnDeveloper(argDeveloperId);
		developer.setActive(false);
		//this.unassignBugs(developer);
		
		this.developerRepo.saveAndFlush(developer);
	}

	/*private void unassignBugs(Developer argDeveloper)
	{
		List<Ticket> developerBugs = this.getBugsBy(argDeveloper.getId());
		developerBugs.stream().forEach(bug -> {
			bug.setDeveloper(null);
			this.bugService.update(bug.getIssueId(), bug);
		});
	}*/

	private List<Ticket> getBugsBy(Integer argDeveloperId)
	{
		return this.ticketService.getBugsBy(argDeveloperId);
	}

	public Developer update(Integer argDeveloperId, Developer argDeveloper)
	{
		checkDeveloper(argDeveloperId);
		
		argDeveloper.setId(argDeveloperId);
		return this.developerRepo.saveAndFlush(argDeveloper);
	}

	private void checkDeveloper(Integer argDeveloperId)
	{
		Developer developer = this.developerRepo.findOne(argDeveloperId);
		if(developer == null) {
			throw new RuntimeException("Developer not exists");
		}
	}
	
	private Developer checkAndReturnDeveloper(Integer argDeveloperId)
	{
		Developer developer = this.developerRepo.findOne(argDeveloperId);
		if(developer == null) {
			throw new RuntimeException("Developer not exists");
		}
		return developer;
	}

	public Integer count()
	{
		return this.developerRepo.findAll().size();
	}
}
