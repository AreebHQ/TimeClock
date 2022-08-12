package com.example.TimeClock.Services;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import com.example.TimeClock.Models.SignUpRequest;
import com.example.TimeClock.Repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Service
public class ShiftService {

  @Autowired
  ShiftRepository shiftRepository;


  public ResponseEntity<?> startShift()
  {
    Shift shift = new Shift();
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    shift.setShiftDate(LocalDate.now().toString());
    shift.setStartShift(timeStamp);
    shift.setShiftActive(true);
    shift.setBreakActive(false);
    shift.setLunchActive(false);
    System.out.println(shift.toString());

    shiftRepository.save(shift);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<List<Shift>> getShifts() {
    return new ResponseEntity<>(shiftRepository.findAll(),HttpStatus.OK);
  }
}
