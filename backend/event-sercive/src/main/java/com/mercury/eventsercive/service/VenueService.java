package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Venue;
import com.mercury.eventsercive.dao.VenueDao;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueDao venueDao;

    @Autowired
    private EventService eventService;

    public void save(Venue venue) {venueDao.save(venue);}

    public Venue getVenueById(Integer id) {
        Optional<Venue> v = venueDao.findById(id);
        if (v.isPresent()) {
            return v.get();
        } else {
            return null;
        }
    }

    public Venue getVenueByName(String name) {
        return venueDao.findByName(name);
    }

    public Response update(Venue venue) {
        Venue v = venueDao.findById(venue.getId()).get();
        v.setName(venue.getName());
        v.setAddress(venue.getAddress());
        v.setOwner(venue.getOwner());
        v.setPhone(venue.getPhone());
        v.setImage(venue.getImage());
        v.setCapacity(venue.getCapacity());
        try {
            venueDao.save(v);
        } catch (Exception e) {
            return new Response(false);
        }
        return new Response(true);
    }

    public void deleteById(int id) {
        eventService.getEventsByVenue(id).forEach(event -> eventService.deleteById(event.getId()));
        venueDao.deleteById(id);
    }
}
