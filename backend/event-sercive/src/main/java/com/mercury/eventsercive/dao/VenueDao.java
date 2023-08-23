package com.mercury.eventsercive.dao;

import com.mercury.eventsercive.bean.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueDao extends JpaRepository<Venue, Integer> {
    public Venue findByName(String name);
}
