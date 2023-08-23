package com.mercury.eventsercive.controller;

import com.mercury.eventsercive.bean.Event;
import com.mercury.eventsercive.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {return eventService.getEventById(id);}

    @GetMapping("/category/{cid}")
    public List<Event> getEventsByCategory(@PathVariable int cid) {return eventService.getEventsByCategory(cid);}

    @PostMapping
    public void save(@RequestBody Event event) {eventService.save(event);}
}
