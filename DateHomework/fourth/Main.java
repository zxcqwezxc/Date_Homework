import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final int SIXTY = 60;
    private static final int TWENTY_FOUR = 24;
    private static final int ONE_THOUSAND = 1000;
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.uuuu HH:mm:ss");
        System.out.println("Ввод: ");
        String input = IN.nextLine();
        Pattern pattern = Pattern.compile("(([0-2][0-9]|3[0-1]).(0[1-9]|1[0-2]).\\d{4}) ([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
        Matcher matcher = pattern.matcher(input);
        LinkedList<Long> millisecondsList = new LinkedList<>();
        LinkedList<String> dateList = new LinkedList<>();

        try {
            while (matcher.find()) {
                format.parse(matcher.group()).setSeconds(0);
                millisecondsList.add(format.parse(matcher.group()).getTime());
                dateList.add((matcher.group()));
            }
        } catch (ParseException e) {
            System.out.println("Ошибка! Выключаюсь");
            System.exit(ONE_THOUSAND);
        }

        for (String date : dateList) {
            try {
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT));
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка! Выключаюсь");
                System.exit(ONE_THOUSAND);
            }
        }

        long difference = 0;
        for (int i = 0; i < millisecondsList.size(); i += 2) {
            difference += millisecondsList.get(i + 1) - millisecondsList.get(i);
        }

        long differenceInMinutes = difference / (SIXTY * ONE_THOUSAND) % SIXTY;
        long differenceInHours = difference / (SIXTY * SIXTY * ONE_THOUSAND) % TWENTY_FOUR;

        System.out.println("Вывод:\n" + differenceInHours + "-" + differenceInMinutes);
    }
}