package com.example.app.repositories;

import com.example.app.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository <Room,Long> {
}
