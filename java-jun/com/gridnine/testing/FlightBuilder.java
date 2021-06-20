package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightBuilder {
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);//Вылет через 3 дня/текущий момент времени
        return Arrays.asList(
                //A normal flight with two hour duration - обычный полет с продолжительностью 2 часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight - перелёт с промежуточными посадками
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past - Вылет на несколько дней раньше
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives - рейс, который отправляется до прибытия
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time - перелёт с наземным временем более 2-ух часов
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time - другой рейс, с наземным временем более 2-ух часов
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");//необходимо чётное кол-во дат/вылетов
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);   //начальная емкость списочного массива
        for (int i = 0; i < (dates.length - 1); i += 2) {   //Считываем данные из массива
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }

}
