import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {

    private static final int DEFAULT_YEAR = 70;
    private static  final String INPUT_REGEX = "^ ?(([01][\\d]|[\\d]|2[0-3]):([0-5][\\d]) ([01][\\d]|[\\d]|2[0-3]):([0-5][\\d]) ?)+$";

    public static void main(String[] args) {


        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date min = new Date(DEFAULT_YEAR, 0, 1, 0, 0);
        Date max = new Date(DEFAULT_YEAR, 0, 2, 0, 0);
        ArrayList<Date> enter = new ArrayList<>();
        ArrayList<Date> exit = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        String[] time;
        String cycle = "";

        Scanner in = new Scanner(System.in);
        System.out.println("Введите время входа и выхода для каждого человека через пробел");

        while (in.hasNextLine()) {
            cycle = in.nextLine();
            if (cycle.matches(INPUT_REGEX)) {
                tmp.append(cycle).append(" ");
            }
            if (cycle.equals("")) {
                break;
            }
        }

        time = tmp.toString().split(" ");

        for (int i = 0; i < time.length; ++i) {
            Date localtime = null;
            try {
                localtime = formatter.parse(time[i]);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            if (i == 0) {
                enter.add(0, localtime);
            } else if (i == 1) {
                exit.add(0, localtime);
            } else if ((i % 2 == 0)) {
                enter.add(i / 2, localtime);
            } else {
                exit.add(i / 2 % 2, localtime);
            }
        }


        for (int i = 0; i < enter.size(); ++i) {
            if ((enter.get(i).after(min)) && (enter.get(i).before(max))) {
                min = enter.get(i);
            }
            if ((exit.get(i).before(max)) && (exit.get(i).after(min))) {
                max = exit.get(i);
            }
        }

        System.out.println(formatter.format(min) + " " + formatter.format(max));
    }
}