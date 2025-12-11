package org.isep;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class AirlineTest {
    @Test
    public void testFlightCreation(){
        Airport origin = new Airport("CDG", "Paris", "Charles de Gaulle");
        Airport destination = new Airport("JFK", "New York", "Kennedy");
        LocalDateTime departure = LocalDateTime.of(2023, 12, 25, 10, 30);
        LocalDateTime arrival = LocalDateTime.of(2023, 12, 25, 18, 30);

        Flight flight = new Flight(101, origin, destination, departure, arrival);

        // API JUnit : assertEquals(attendu, réel)
        assertEquals(101, flight.getFlightNumber());
        assertEquals("CDG", flight.getOrigine().getName());
        assertEquals(FlightStatus.ON_TIME, flight.getStatus(), "Le statut par défaut doit être ON_TIME");
    }

    @Test
    public void testAircraftAvailability(){

    }

    @Test
    public void testPassengerCapacity(){

    }
}
