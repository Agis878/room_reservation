package com.example.service;

import com.example.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoomService {
    public List<Room> findAll();
}
