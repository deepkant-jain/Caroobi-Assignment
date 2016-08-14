package com.deepkant.tmsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepkant.tmsapp.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>
{
	@Query(value="from Ticket b where b.developer.id is null order by b.issueId asc")
	public List<Ticket> findUnassigned();

	@Override
	@Query(value="from Ticket b order by b.issueId asc")
	public List<Ticket> findAll();

	@Query(value="from Ticket b where b.developer.id = :developerId order by b.issueId asc")
	public List<Ticket> getBugsBy(@Param(value = "developerId") Integer argDeveloperId);
}
