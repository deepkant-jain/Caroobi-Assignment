package com.deepkant.tmsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deepkant.tmsapp.model.Developer;
import com.deepkant.tmsapp.service.DeveloperService;

@RestController
@RequestMapping("/developer")
public class UserController
{
	@Autowired
	private DeveloperService developerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Developer> listAllDevelopers()
	{
		return this.developerService.findAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Developer createDeveloper(@RequestBody Developer argDeveloper)
	{
		return this.developerService.create(argDeveloper);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Developer developerInformation(@PathVariable(value = "id") Integer argDeveloperId)
	{
		return this.developerService.findBy(argDeveloperId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteDeveloper(@PathVariable(value = "id") Integer argDeveloperId)
	{
		this.developerService.delete(argDeveloperId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Developer updateDeveloper(@RequestBody Developer argDeveloper,
									 @PathVariable(value = "id") Integer argDeveloperId)
	{
		return this.developerService.update(argDeveloperId, argDeveloper);
	}
}
