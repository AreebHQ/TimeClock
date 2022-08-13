package com.example.TimeClock.Controllers;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import com.example.TimeClock.Models.SignUpRequest;
import com.example.TimeClock.Services.ShiftService;
import com.example.TimeClock.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  ShiftService shiftService;
  @Autowired
  UserService userService;


  @PostMapping("/endLunch")
  public ResponseEntity<Shift> endLunch()
  {
    System.out.println("endLunch called");
    return shiftService.endLunch();
  }

  @PostMapping("/startLunch")
  public ResponseEntity<Shift> startLunch()
  {
    System.out.println("startLunch called");
    return shiftService.startLunch();
  }


  @PostMapping("/endBreak")
  public ResponseEntity<Shift> endBreak()
  {
    System.out.println("endBreak called");
    return shiftService.endBreak();
  }

  @PostMapping("/startBreak")
  public ResponseEntity<Shift> startBreak()
  {
    System.out.println("startBreak called");
    return shiftService.startBreak();
  }

  @PostMapping("/endShift")
  public ResponseEntity<Shift> endShift()
  {
    System.out.println("endShift called");
    return shiftService.endShift();
  }


  @PostMapping("/startShift")
  public ResponseEntity<?> startShift()
  {
    System.out.println("Called startShift");
    return shiftService.startShift();
  }

  @GetMapping("/shifts")
  public ResponseEntity<List<Shift>> getShifts()
  {
    return shiftService.getShifts();
  }

  @PostMapping("/signUp")
  public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest)
  {
    System.out.println("Called SignUp");
    return userService.signUp(signUpRequest);
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers()
  {
    return userService.getUsers();
  }
}
