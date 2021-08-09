import java.sql.Connection;
import java.sql.DriverManager;

public class T4Tongji {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connectionLocal = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false", "root", "root");


        //查工单
        String sql=
                "SELECT id wid,title,question_category_id,question_category_name,process_instance_id,work_flow_id FROM t_work_order WHERE work_flow_id in (\n" +
                "\n" +
                "SELECT work_flow_id FROM t_work_order where date(create_time)>='2021-03-01' and date(create_time)<='2021-03-31'\n" +
                "AND work_flow_id in (\n" +
                "-- 1 启用\n" +
                "SELECT id FROM t_work_flow_info WHERE STATUS=1\n" +
                ")GROUP BY  work_flow_id HAVING COUNT(*)>30\n" +
                "\n" +
                ")and date(create_time)>='2021-03-01' and date(create_time)<='2021-03-31'";


        //查环节


    }
}
