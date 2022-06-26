import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    private static final String INPUT_REGEX = "^[\\d]{2}.[\\d]{1,2}.[\\d]{4}$";
    private static final int FOUR = 4;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean check = false;
        String input = "";
        String[] inputTwo;

        System.out.println("Ввод");

        while (!check) {
            input = in.nextLine();
            if (input.matches(INPUT_REGEX)) {
                check = true;
            } else {
                System.out.println("Ошибка ввода");
            }
        }

        inputTwo = input.split("\\.");

        Calendar calendarStart = new GregorianCalendar(Integer.parseInt(inputTwo[2]), Integer.parseInt(inputTwo[1]) - 1, Integer.parseInt(inputTwo[0]));
        Calendar calendarEnd = new GregorianCalendar(Integer.parseInt(inputTwo[2]), Integer.parseInt(inputTwo[1]), Integer.parseInt(inputTwo[0]));

        do {
            if (calendarStart.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendarStart.add(Calendar.DATE, 1);
            }
            System.out.println(calendarStart.get(Calendar.DAY_OF_MONTH) + "." + (calendarStart.get(Calendar.MONTH) + 1) + "." + calendarStart.get(Calendar.YEAR));
            calendarStart.add(Calendar.DATE, FOUR);
        } while (calendarStart.before(calendarEnd));
    }
}
