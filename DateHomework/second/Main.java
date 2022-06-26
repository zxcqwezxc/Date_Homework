import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final String SINGLE_DATE_REGEX = "^[\\d]{2}.[\\d]{2}.[\\d]{4}$";
    private static final String PHASE_DATE_REGEX = "^[\\d]{2}.[\\d]{2}.[\\d]{4} ?- ?[\\d]{2}.[\\d]{2}.[\\d]{4}$";
    private static final String REPLACE_REGEX = "[\\[\\]\\'\\,\"]";
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String[] inputTwo;
        ArrayList<Date> single = new ArrayList<>();
        ArrayList<Date> phaseStart = new ArrayList<>();
        ArrayList<Date> phaseEnd = new ArrayList<>();
        String input = "";
        String inputBooking = "";
        Date bookingDate = null;
        Date[] bookingPhaseDate = new Date[2];
        boolean oneDate = true;
        boolean answer = false;

        System.out.println("Ввод:");
        input = enterLine();

        inputTwo = input.replaceAll(REPLACE_REGEX, "").split(" ");

        for (int i = 0; i < inputTwo.length; ++i) {
            Date bufferDate = null;
            Date phaseStartBufferDate = null;
            Date phaseEndBufferDate = null;

            if (inputTwo[i].matches(SINGLE_DATE_REGEX)) {
                try {
                    bufferDate = formatter.parse(inputTwo[i]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                single.add(bufferDate);
            }
            if (inputTwo[i].matches(PHASE_DATE_REGEX)) {
                String[] bufferString;
                bufferString = inputTwo[i].replace("-", " ").split(" ");

                try {
                    phaseStartBufferDate = formatter.parse(bufferString[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phaseStart.add(phaseStartBufferDate);

                try {
                    phaseEndBufferDate = formatter.parse(bufferString[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phaseEnd.add(phaseEndBufferDate);
            }

        }

        System.out.println("Введите дату/промежуток бронирования");
        inputBooking = enterLine();

        if (inputBooking.matches(SINGLE_DATE_REGEX)) {
            try {
                bookingDate = formatter.parse(inputBooking);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (inputBooking.matches(PHASE_DATE_REGEX)) {
            oneDate = false;
            String[] bufferBooking;
            bufferBooking = inputBooking.replace("-", " ").split(" ");
            try {
                bookingPhaseDate[0] = formatter.parse(bufferBooking[0]);
                bookingPhaseDate[1] = formatter.parse(bufferBooking[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j < phaseStart.size(); ++j) {
            if (!oneDate) {
                if ((!((bookingPhaseDate[0].after(phaseStart.get(j))) && (bookingPhaseDate[0].before(phaseEnd.get(j))))
                        || !((bookingPhaseDate[1].after(phaseStart.get(j))) && (bookingPhaseDate[1].before(phaseEnd.get(j)))))) {
                    answer = true;
                }
            }
            if ((oneDate) && !(single.contains(bookingDate))) {
                answer = true;
            }
            if ((oneDate) && (((bookingDate.after(phaseStart.get(j))) && (bookingDate.before(phaseEnd.get(j)))
                    || bookingDate.equals(phaseStart.get(j)) || bookingDate.equals(phaseEnd.get(j))))) {
                answer = false;
            }
            if (!oneDate) {
                if ((((bookingPhaseDate[0].after(phaseStart.get(j))) && (bookingPhaseDate[0].before(phaseEnd.get(j))))
                        || ((bookingPhaseDate[1].after(phaseStart.get(j))) && (bookingPhaseDate[1].before(phaseEnd.get(j)))))
                        || ((bookingPhaseDate[0].equals(phaseStart.get(j))) || (bookingPhaseDate[0].equals(phaseEnd.get(j))))
                        || ((bookingPhaseDate[1].equals(phaseStart.get(j))) || (bookingPhaseDate[1].equals(phaseEnd.get(j))))) {
                    answer = false;
                }
            }
        }

        for (int k = 0; k < single.size(); ++k) {
            if ((!oneDate) && (bookingPhaseDate[0].before(single.get(k)) && (bookingPhaseDate[1]).after(single.get(k)))) {
                answer = false;
            }
        }

        System.out.println(answer);

    }

    public static String enterLine() {
        boolean check = false;
        String enter = "";
        while (!check) {

            if (IN.hasNextLine()) {
                enter = IN.nextLine();
                check = true;
            } else {
                System.out.println("Ошибка ввода, выключаюсь");
                System.exit(0);
            }
        }
        return enter;
    }
}