package com.mercury.eventsercive.dao;

import com.mercury.eventsercive.bean.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDao extends JpaRepository<Event, Integer> {
    public Event findByName(String name);

    public List<Event> findByCategoryId(Integer cid);

    public List<Event> findByVenueId(Integer vid);
}
