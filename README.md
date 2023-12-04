# Worldwide Windsurfers Weather Service application

#### This project is a simple RESTful web API that connect with Weatherbit api - weather forecast 16 day and returns the best location for windsurfing from selected cities for chosen day.
#### Selected cities:

* JASTARNIA(Poland),
* BRIDGETOWN(Barbados),
* FORTALEZA(Brazil),
* PISSOURI(Cyprus),
* LE_MORNE(Mauritius);

All cities are coded in enum City, if you want to check more locations just add another city to enum.

## Installing

### 1. Clone this repository

```bash
  git clone https://github.com/kbledzki/Windsurfers-Weather-Service-App.git
```

Go to project directory

```bash
  cd Windsurfers-Weather-Service
```

### 2. Build project and perform tests

* Open terminal in project directory and run:

```bash
  mvn clean install
```

## Running the application

### 1. Start application in IntelliJ or in terminal:

```bash
mvn spring-boot:run
```

## Test the application by making HTTP requests to the following endpoints:

### Operation : Return the best location for windsurfing for selected date (formatted YYYY-MM-DD)

* Endpoint:

       GET /api/v1/weather/{date}
* Example:

       GET /api/v1/weather/2023-12-10
* Response:

       { 
           "cityName": "Le Gros-Morne",
           "temperature": "27.2", 
           "windSpeed": "5.9", 
           "lat": 14.7,
           "lat": -61.0;
       }

