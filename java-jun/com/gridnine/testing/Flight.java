package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class Flight {
    private final List<Segment> segments;

    Flight(final List<Segment> segs) {
        segments = segs;
    }

    List<Segment> getSegments() {
        return segments;
    }

    public static void printList(List<Flight> flights) {
        for(Flight flight : flights) {
            System.out.println(flight);
            System.out.println("<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>");
        }
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
