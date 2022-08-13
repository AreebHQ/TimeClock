package com.example.TimeClock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class TimeClockApplication {

  public static void main(String[] args) {
    SpringApplication.run(TimeClockApplication.class, args);


  }
}

/*
 public class TimeClockApplication {
  public static void main(String[] args) {



    LocalDateTime t = LocalDateTime.now();
    String formatter = t.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.hh:mm:ss"));
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

    System.out.println(formatter);
    System.out.println(timeStamp);
    System.out.println(LocalDate.now().toString());

  }

}

*/
