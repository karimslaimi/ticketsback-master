package com.tekup.ticketsproject.Services;

import com.tekup.ticketsproject.Entities.Tickets;

import java.util.List;

public interface TicketService {

    public List<Tickets> getOpenTickets();
}
