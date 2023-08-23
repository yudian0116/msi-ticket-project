package com.mercury.eventsercive.controller;

import com.mercury.eventsercive.bean.Ticket;
import com.mercury.eventsercive.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable int id) {return ticketService.getTicketById(id);}

    @GetMapping("/event/{eid}")
    public List<Ticket> getTicketsByEvent(@PathVariable int eid) {return ticketService.getTicketsByEvent(eid);}

    @PostMapping
    public void save(@RequestBody Ticket ticket) {ticketService.save(ticket);}
}
