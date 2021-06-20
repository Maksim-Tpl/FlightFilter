package com.gridnine.testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Flight> flights = FlightBuilder.createFlights(); //Получаем тестовый набор перелётов
        FlightFilter flightFilter = new FlightFilter();
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //Считываем данные с клавиатуры
            System.out.print("Выберите сортировку по правилу, указав команду:\n" +
                    "1. Исключить из списка перелёты, выполненные до текущего момента времени. \n" +
                    "2. Исключить из списка сегменты с датой прилёта раньше, чем дата вылета. \n" +
                    "3. Исключить из списка перелёты с проведённым на земле временем более двух часов. \n" +
                    "4. Введите для завершения программы. \n" +
                    "\n   Введите команду: ");
            Integer integer = null;
            int input = integer.parseInt(reader.readLine()); //Приводим введённую строку к типу int
            if (input==1) { //Если введено одно из указанных чисел, то выводим результат на экран
                System.out.println("\n Из списка исключены перелеты до текущего момента времени: \n");
                Flight.printList(flightFilter.departureUntilTheCurrentTime(flights, LocalDateTime.now()));
            } else if (input==2) {
                System.out.println("\n Из списка исключены сегменты с датой прилёта раньше даты вылета: \n ");
                Flight.printList(flightFilter.arrivalEarlierThanDeparture(flights));
            } else if (input==3) {
                System.out.println("\n Из списка исключены перелёты, находящиеся на земле более двух часов: \n");
                Flight.printList(flightFilter.hasBeenOnTheGroundForMoreThanTwoHours(flights));
            } else if (input==4) {
                System.out.println("\n Завершение программы.");
                reader.close();
                return;
            } else { //Если введённое число не совпадает с одним из указанных чисел, то выводим Exception и завершаем программу
                throw new IllegalArgumentException("Ошибка ввода");
            }
        }
    }
}