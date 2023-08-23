package com.mercury.eventsercive.controller;

import com.mercury.eventsercive.bean.Venue;
import com.mercury.eventsercive.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable int id) {return venueService.getVenueById(id);}

    @GetMapping("/name/{name}")
    public Venue getVenueByName(@PathVariable String name) {return venueService.getVenueByName(name);}

    @PostMapping
    public void save(@RequestBody Venue venue) {venueService.save(venue);}

}
