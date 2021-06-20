package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter implements Excluded {
    //Перелёты до текущего момента времени
    public List<Flight> departureUntilTheCurrentTime(List<Flight> flights, LocalDateTime requestedDate) {
        List<Flight> result = new ArrayList<>(flights);
        for(Flight flight : flights) {
            for(Segment segment : flight.getSegments()){
                if(segment.getDepartureDate().isBefore(requestedDate)){
                    result.remove(flight);
                }
            }
        }
        return result;
    }

    //Сегменты с датой прилета раньше даты вылета
    public List<Flight> arrivalEarlierThanDeparture(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        for(Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            for(Segment segment : segments) {
                if(segment.getArrivalDate().isBefore(segment.getDepartureDate())){
                    result.remove(flight);
                }
            }
        }
        return result;
    }

    //Время, проведённое на земле превышает 2 часа
    public List<Flight> hasBeenOnTheGroundForMoreThanTwoHours(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            int timeOnTheGround = 0;
            if (segments.size() > 1) {
                for (int i = 0; i < segments.size() - 1; i++) {
                    timeOnTheGround += Math.abs(Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).toHours());
                } if(timeOnTheGround > 2) {
                    result.remove(flight);
                }
            }
        }
        return result;
    }
}
