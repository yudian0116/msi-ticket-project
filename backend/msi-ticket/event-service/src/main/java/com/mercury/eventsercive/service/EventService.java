package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Category;
import com.mercury.eventsercive.bean.Event;
import com.mercury.eventsercive.dao.CategoryDao;
import com.mercury.eventsercive.dao.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private CategoryDao categoryDao;

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
        Category c = categoryDao.findById(cid).get();
        return eventDao.findByCategory(c);
    }
}
