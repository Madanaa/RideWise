# RideWise - Modular Ride-Sharing System

RideWise is a console-based Java application that demonstrates a simple ride-sharing flow using OOP, SOLID principles, and strategy-based composition.

## Features

- Register riders.
- Register drivers with vehicle types.
- View available drivers.
- Request rides.
- Match drivers using an injectable ride matching strategy.
- Complete rides and generate fare receipts.
- Track ride statuses: `REQUESTED`, `ASSIGNED`, `COMPLETED`, and `CANCELLED`.

## Design

The application is organized into focused packages:

- `model`: domain entities and enums.
- `service`: application services for riders, drivers, and rides.
- `strategy.matching`: driver matching strategies.
- `strategy.fare`: fare calculation strategies.

`RideService` depends on the `RideMatchingStrategy` and `FareStrategy` interfaces, so matching and pricing behavior can be changed without modifying the service logic.

## Implemented Strategies

Ride matching:

- `NearestDriverStrategy`
- `LeastActiveDriverStrategy`

Fare calculation:

- `DefaultFareStrategy`
- `PeakHourFareStrategy`

## Requirements

- Java 21 or newer.
- Maven, if you want to run the project through Maven.

## Run

Compile and run from the project root:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out org.ridewise.Main
```

On Windows PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object FullName)
java -cp out org.ridewise.Main
```

If Maven is installed:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.ridewise.Main"
```
