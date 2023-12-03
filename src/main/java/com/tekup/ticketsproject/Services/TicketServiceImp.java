package com.tekup.ticketsproject.Services;


import com.tekup.ticketsproject.Entities.Enum.STATUS;
import com.tekup.ticketsproject.Entities.Tickets;
import com.tekup.ticketsproject.Repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService{

    private final TicketsRepository ticketRepository;


    @Autowired
    public TicketServiceImp(TicketsRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Tickets> getOpenTickets() {
        return this.ticketRepository.findByStatus(String.valueOf(STATUS.OPEN));
    }
}
