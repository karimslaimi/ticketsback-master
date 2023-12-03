package com.tekup.ticketsproject.Repositories;


import com.tekup.ticketsproject.Entities.Enum.STATUS;
import com.tekup.ticketsproject.Entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findByTitleContaining(String title);
    List<Tickets> findByStatus(String status);
}