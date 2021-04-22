package com.terence.parking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpaceApplicationTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void shouldShowErrorMessageIfFileInvalid() {
    ParkingSpaceApplication.main(new String[] {"invalid-path"});
    assertEquals("Something went wrong. Enter absolute path to file.\n", outContent.toString());
  }

  @Test
  void shouldShowErrorMessageIfZeroCommandLineArguments() {
    ParkingSpaceApplication.main(new String[] {});
    assertEquals("Invalid input. Only one argument expected.\n", outContent.toString());
  }

  @Test
  void shouldShowErrorMessageIfMoreThanOneCommandLineArgument() {
    ParkingSpaceApplication.main(new String[] {"a", "b"});
    assertEquals("Invalid input. Only one argument expected.\n", outContent.toString());
  }

  @Test
  void endToEndSuite() throws Exception {
    String testPath = getAbsolutePathToResource("test_input.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected =
        "Accept MotorcycleLot1\n"
            + "Accept CarLot1\n"
            + "MotorcycleLot1 2\n"
            + "Accept CarLot2\n"
            + "Accept CarLot3\n"
            + "Reject\n"
            + "CarLot3 6\n";

    assertEquals(expected, outContent.toString());
  }

  private String getAbsolutePathToResource(String resourceName) throws URISyntaxException {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(resourceName);
    File file = Paths.get(resource.toURI()).toFile();
    String pathToTest = file.getAbsolutePath();
    return pathToTest;
  }
}
