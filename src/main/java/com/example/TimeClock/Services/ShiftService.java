package com.example.TimeClock.Services;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import com.example.TimeClock.Models.SignUpRequest;
import com.example.TimeClock.Repositories.ShiftRepository;
import com.example.TimeClock.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ShiftService {

  @Autowired
  ShiftRepository shiftRepository;
  @Autowired
  UserRepository userRepository;

  public ResponseEntity<List<Shift>> getShifts() {
    //List shifts = shiftRepository.findAll().stream().filter(s -> s.isShiftActive()).collect(Collectors.toList());
    return new ResponseEntity<>(shiftRepository.findAll(),HttpStatus.OK);
  }

  public ResponseEntity<?> startShift()
  {
    //todo : need to provide user
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(!checkShift.isEmpty())
    {
      System.out.println("Shift Present: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    //if shift doesn't exist
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

  public ResponseEntity<Shift> endShift()
  {
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(checkShift.isEmpty() || !checkShift.get().isShiftActive() || checkShift.get().isBreakActive() || checkShift.get().isLunchActive())
    {
      System.out.println("Shift doesn't exist or not-active or lunch-break is active: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    checkShift.get().setEndShift(timeStamp);
    checkShift.get().setShiftActive(false);
    shiftRepository.save(checkShift.get());
    return new ResponseEntity<>(checkShift.get(),HttpStatus.OK);
  }

  public ResponseEntity<Shift> startBreak()
  {
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(checkShift.isEmpty() || !checkShift.get().isShiftActive() || checkShift.get().isBreakActive() || checkShift.get().isLunchActive())
    {
      System.out.println("Shift doesn't exist or not-active or lunch-break is active: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    checkShift.get().setStartBreak(timeStamp);
    checkShift.get().setBreakActive(true);
    shiftRepository.save(checkShift.get());
    return new ResponseEntity<>(checkShift.get(),HttpStatus.OK);
  }

  public ResponseEntity<Shift> endBreak()
  {
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(checkShift.isEmpty() || !checkShift.get().isShiftActive() || !checkShift.get().isBreakActive() || checkShift.get().isLunchActive())
    {
      System.out.println("Shift doesn't exist or not-active or lunch-break is active: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    checkShift.get().setEndBreak(timeStamp);
    checkShift.get().setBreakActive(false);
    return new ResponseEntity<>(shiftRepository.save(checkShift.get()),HttpStatus.OK);
  }

  public ResponseEntity<Shift> startLunch()
  {
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(checkShift.isEmpty() || !checkShift.get().isShiftActive() || checkShift.get().isBreakActive() || checkShift.get().isLunchActive())
    {
      System.out.println("Shift doesn't exist or not-active or lunch-break is active: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    checkShift.get().setStartLunch(timeStamp);
    checkShift.get().setLunchActive(true);
    shiftRepository.save(checkShift.get());
    return new ResponseEntity<>(checkShift.get(),HttpStatus.OK);
  }

  public ResponseEntity<Shift> endLunch()
  {
    Optional<Shift> checkShift = Optional.ofNullable(shiftRepository.findByShiftDateAndUser(LocalDate.now().toString(),userRepository.findById(2)));
    // if shift exist
    if(checkShift.isEmpty() || !checkShift.get().isShiftActive() || checkShift.get().isBreakActive() || !checkShift.get().isLunchActive())
    {
      System.out.println("Shift doesn't exist or not-active or lunch-break is active: " + checkShift.toString());
      return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
    }
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    checkShift.get().setEndLunch(timeStamp);
    checkShift.get().setLunchActive(false);
    return new ResponseEntity<>(shiftRepository.save(checkShift.get()),HttpStatus.OK);
  }


}
