package com.gridnine.testing;

import java.util.List;
import java.time.LocalDateTime;

//Исключаемые
public interface Excluded {
    //Перелёты до текущего момента времени
    List<Flight> departureUntilTheCurrentTime(List<Flight> flights, LocalDateTime requestedDate);
    //Сегменты с датой прилета раньше даты вылета
    List<Flight> arrivalEarlierThanDeparture(List<Flight> flights);
    //Время, проведённое на земле превышает 2 часа
    List<Flight> hasBeenOnTheGroundForMoreThanTwoHours(List<Flight> flights);
}
