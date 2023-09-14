package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Event;
import com.mercury.eventsercive.bean.Ticket;
import com.mercury.eventsercive.dao.EventDao;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TicketService ticketService;

    public void save(Event event) {eventDao.save(event);}

    public Event getEventById(Integer id) {
        Optional<Event> e = eventDao.findById(id);
        if (e.isPresent()) {
            return e.get();
        } else {
            return null;
        }
    }

    public List<Event> getEventsByCategory(int cid) {
        return eventDao.findByCategoryId(cid);
    }

    public List<Event> getEventsByVenue(int vid) {
        return eventDao.findByVenueId(vid);
    }

    public Response update(Event event) {
        Event e = eventDao.findById(event.getId()).get();
        e.setName(event.getName());
        e.setCategory(event.getCategory());
        e.setStartTime(event.getStartTime());
        e.setEndTime(event.getEndTime());
        e.setImage(event.getImage());
        e.setDescription(event.getDescription());
        e.setVenue(event.getVenue());
        try {
            eventDao.save(e);
        } catch (Exception ex) {
            return new Response(false);
        }
        return new Response(true);
    }

    public void deleteById(int id) {
        List<Ticket> tickets = ticketService.getTicketsByEvent(id);
        for (Ticket ticket : tickets) {
            ticketService.deleteById(ticket.getId());
        }
        eventDao.deleteById(id);
    }
}
