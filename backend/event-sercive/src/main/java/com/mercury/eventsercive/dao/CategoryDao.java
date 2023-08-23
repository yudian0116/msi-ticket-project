package com.mercury.eventsercive.dao;

import com.mercury.eventsercive.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
