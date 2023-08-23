package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Venue;
import com.mercury.eventsercive.dao.VenueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueDao venueDao;

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
}
