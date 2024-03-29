package com.tekup.ticketsproject.Services;

import com.tekup.ticketsproject.DTO.TicketsDTO;
import com.tekup.ticketsproject.Entities.Tickets;

import java.util.List;

public interface TicketService {

    public List<TicketsDTO> getTickets(String username) throws Exception;

    TicketsDTO create(TicketsDTO ticketsDTO, String username) throws Exception;

    void deleteTicket(Long id);
}
