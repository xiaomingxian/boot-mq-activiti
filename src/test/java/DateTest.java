import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTest {

    public static void main(String[] args) {

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -0);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(cal.getTime());



        System.out.println(dateStr);


    }


}


