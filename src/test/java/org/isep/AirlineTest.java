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
        Aircraft plane = new Aircraft(150, "Boeing 737", "F-GZTA");
        Airport airport = new Airport("Test", "Test", "Test");

        Flight flight1 = new Flight(201, airport, airport,
                LocalDateTime.of(2023, 1, 1, 10, 0),
                LocalDateTime.of(2023, 1, 1, 12, 0));
        plane.assignFlight(flight1);

        boolean isAvailableOverlap = plane.checkAvailability(
                LocalDateTime.of(2023, 1, 1, 11, 0),
                LocalDateTime.of(2023, 1, 1, 13, 0)
        );
        assertFalse(isAvailableOverlap, "L'avion ne devrait pas être dispo (chevauchement)");

        boolean isAvailableLater = plane.checkAvailability(
                LocalDateTime.of(2023, 1, 1, 13, 0),
                LocalDateTime.of(2023, 1, 1, 15, 0)
        );
        assertTrue(isAvailableLater, "L'avion devrait être dispo (créneau libre)");
    }

    @Test
    public void testPassengerCapacity(){

    }
}
