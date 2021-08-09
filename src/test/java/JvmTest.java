import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JvmTest {
    public static void main(String[] args) {

        System.out.println(
                getDate12MounthAgo()
        );
    }

    public static String getDate12MounthAgo(){
        Date date = new Date();

        Calendar from  =  Calendar.getInstance();
        from.setTime(date);
        String str1 = from.get(Calendar.YEAR)+"-"+fillZero(from.get(Calendar.MONTH)+1);
        from.add(Calendar.MONTH, -12);//11个月前
        String str2 = from.get(Calendar.YEAR)+"-"+fillZero(from.get(Calendar.MONTH)+1);


        return  str2+"-01";

    }


    public static String fillZero(int i){
        String month = "";
        if(i<10){
            month = "0" + i;
        }else{
            month = String.valueOf(i);
        }
        return month;
    }

}
