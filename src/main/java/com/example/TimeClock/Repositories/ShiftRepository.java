package com.example.TimeClock.Repositories;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

  Shift findByShiftDate(String date);
  Shift findByShiftDateAndUser(String date, User user);
}
