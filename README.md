# Parking Lot System

## Dependencies
1. [Gradle](https://gradle.org/install/)
2. [Java 11 and above](https://java.com/en/download/help/download_options.html)

## Scripts
```sh
# Build code for local development
./gradlew build
```

```sh
# Test
./gradlew test
```

```sh
# Build executable JAR
./gradlew shadowJar
```

```sh
# Run
java -jar build/libs/parking_system.jar <path_to_input_file>
```

Sample input file
```sh
3 4
Enter motorcycle SGX1234A 1613541902
Enter car SGF9283P 1613541902
Exit SGX1234A 1613545602
Enter car SGP2937F 1613546029
Enter car SDW2111W 1613549730
Enter car SSD9281L 1613549740
Exit SDW2111W 1613559745
```
