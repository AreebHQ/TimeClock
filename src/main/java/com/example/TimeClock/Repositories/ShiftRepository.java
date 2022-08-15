package com.example.TimeClock.Repositories;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

  Shift findByShiftDate(String date);
  Shift findByShiftDateAndUser(String date, User user);

  List<Shift> findByUserOrderByShiftDateDesc(User user);
}
