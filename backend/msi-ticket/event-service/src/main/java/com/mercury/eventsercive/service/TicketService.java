package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Ticket;
import com.mercury.eventsercive.dao.TicketDao;

import dto.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    public void save(Ticket ticket) {ticketDao.save(ticket);}

    public Ticket getTicketById(int id) {
        Optional<Ticket> t = ticketDao.findById(id);
        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }

    public List<TicketResponse> getTicketsFromList(List<Integer> ticketId) {
        return ticketDao.findTicketByIdIn(ticketId).stream()
                .map(ticket ->
                    TicketResponse.builder()
                            .tid(ticket.getId())
                            .price(ticket.getPrice())
                            .stock(ticket.getStock())
                            .build()
                ).toList();
    }

    public List<Ticket> getTicketsByEvent(int event_id) {
        return ticketDao.findByEventId(event_id);
    }
}
