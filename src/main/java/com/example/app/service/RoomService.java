package com.example.app.service;

import com.example.app.model.Room;
import com.example.app.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }
}
