package com.terence.parking.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

  @Test
  void sayHello() {
    Sample sample = new Sample();

    assertEquals("Hello World", sample.hello());
  }

//  @Test
//  void sayBye() {
//    Sample sample = new Sample();
//
//    assertEquals("Bye Bye", sample.hello());
//  }
}
