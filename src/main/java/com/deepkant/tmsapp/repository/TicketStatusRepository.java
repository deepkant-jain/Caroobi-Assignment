package com.deepkant.tmsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepkant.tmsapp.model.TicketStatus;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer>
{

}
