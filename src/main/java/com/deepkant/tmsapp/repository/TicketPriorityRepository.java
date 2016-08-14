package com.deepkant.tmsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepkant.tmsapp.model.TicketPriority;

public interface TicketPriorityRepository extends JpaRepository<TicketPriority, Integer>
{

}
