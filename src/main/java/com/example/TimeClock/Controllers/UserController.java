package com.example.TimeClock.Controllers;

import com.example.TimeClock.Entities.Shift;
import com.example.TimeClock.Entities.User;
import com.example.TimeClock.Models.SignUpRequest;
import com.example.TimeClock.Services.ShiftService;
import com.example.TimeClock.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  ShiftService shiftService;
  @Autowired
  UserService userService;


  @GetMapping("/currentShift")
  public ResponseEntity<?> currentShift(Authentication authentication)
  {
    return shiftService.currentShift(authentication.getName());
  }

  @PostMapping("/endLunch")
  public ResponseEntity<?> endLunch(Authentication authentication)
  {
    return shiftService.endLunch(authentication.getName());
  }

  @PostMapping("/startLunch")
  public ResponseEntity<?> startLunch(Authentication authentication)
  {
    return shiftService.startLunch(authentication.getName());
  }

  @PostMapping("/endBreak")
  public ResponseEntity<?> endBreak(Authentication authentication)
  {
    System.out.println("endBreak called");
    return shiftService.endBreak(authentication.getName());
  }

  @PostMapping("/startBreak")
  public ResponseEntity<?> startBreak(Authentication authentication)
  {
    return shiftService.startBreak(authentication.getName());
  }

  @PostMapping("/endShift")
  public ResponseEntity<?> endShift(Authentication authentication)
  {
    return shiftService.endShift(authentication.getName());
  }

  @PostMapping("/startShift")
  public ResponseEntity<?> startShift(Authentication authentication)
  {
    return shiftService.startShift(authentication.getName());
  }

  @GetMapping("/shifts")
  public ResponseEntity<List<Shift>> getShifts(Authentication authentication)
  {
     return shiftService.getShifts(authentication.getName());
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers()
  {
    return userService.getUsers();
  }
}
