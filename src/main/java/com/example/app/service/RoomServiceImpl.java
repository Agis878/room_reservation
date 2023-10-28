package com.example.app.service;

import com.example.app.model.Room;
import com.example.app.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomService) {
        this.roomRepository = roomService;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

}
