package com.mercury.eventsercive.dao;

import com.mercury.eventsercive.bean.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findByEventId(int event_id);

    public List<Ticket> findTicketByIdIn(List<Integer> ticket_id);

}
