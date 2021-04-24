package com.terence.parking;

import com.terence.parking.parkinglot.ParkingLot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    ParkingLot.resetInstance();
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

  @Test
  void shouldThrowErrorIfParkingLotNotInitialised() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_1.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Line 1: Parking Lot not initialised.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingAndThrowInvalidArgumentsValidationError() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_2.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Line 2: Invalid number of arguments. Expected 4 but got 3.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingAndThrowInvalidTimestampValidationError() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_3.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Accept MotorcycleLot1\n" + "Line 3: Invalid timestamp format.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingAndThrowInvalidCommandError() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_4.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Line 2: Invalid command.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingIfParkingVehicleNumberThatAlreadyExists() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_5.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Accept MotorcycleLot1\n" +
                      "Line 3: Vehicle number already exists.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingIfExitingVehicleNumberThatDoesNotExist() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_6.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Line 2: Vehicle number does not exist.\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void shouldStopProcessingIfParkingLotInitialisedASecondTime() throws Exception {
    String testPath = getAbsolutePathToResource("invalid_test_input_7.txt");

    ParkingSpaceApplication.main(new String[] {testPath});

    String expected = "Line 2: Parking Lot already initialised.\n";

    assertEquals(expected, outContent.toString());
  }

  private String getAbsolutePathToResource(String resourceName) throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(resourceName);
    File file = Paths.get(resource.toURI()).toFile();
    return file.getAbsolutePath();
  }
}
