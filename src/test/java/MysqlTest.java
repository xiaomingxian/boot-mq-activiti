import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.IndexedColors;
import pojo.ExcelUtil;
import pojo.FormValue;
import pojo.OrderMsg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MysqlTest
{


    public static void main(String[] args) {


        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://172.16.0.242:3306/db_work_flow","guoshiying","iG8CeqlNF6ukhx4l");




//            //变量
//            String sql = "SELECT work_order_id,form_value  FROM t_work_flow_variable    where task_type='startEvent' and DATE(create_time)>='2020-06-01' and DATE(create_time)<='2021-05-31'\n" +
//                    "\n" +
//                    "and work_order_id in (\n" +
//                    "\t\t\tSELECT id\n" +
//                    "                     \n" +
//                    "                     \n" +
//                    "                       FROM t_work_order where id in ( \n" +
//                    "                    select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31' \n" +
//                    "                    ) and `status` in (2,3) and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
//                    "\n" +
//                    ")";




            //变量  走完盖章就行
            String sql = "SELECT work_order_id,form_value  FROM t_work_flow_variable    where task_type='startEvent' and DATE(create_time)>='2020-06-01' and DATE(create_time)<='2021-05-31'\n" +
                    "\n" +
                    "and work_order_id in (\n" +
                    "\t\t\tSELECT id\n" +
                    "                     \n" +
                    "                     \n" +
                    "                       FROM t_work_order where id in ( \n" +
                    "                    select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null and status=-1   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31' \n" +
                    "                    )  and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
                    "\n" +
                    ")";





//            String sqlP ="select work_order_id,processor_name from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n";

            /////  走完盖章就行
            String sqlP ="select work_order_id,processor_name from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null and status=-1   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n";



            //办理人
            Map<String, String> order  = new HashMap<>();

            PreparedStatement pstmtp = connection.prepareStatement(sqlP);
            ResultSet rsp = pstmtp.executeQuery();
            while (rsp.next()) {
                String id = rsp.getString("work_order_id");
                String processor_name = rsp.getString("processor_name");
                order.put(id,processor_name);
//                System.out.println(id+"   "+processor_name);
            }

            rsp.close();
            pstmtp.close();





            Map<String, String> var  = new HashMap<>();

            HashMap<String, FormValue> formValueHashMap = new HashMap<String, FormValue>();


            //变量
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                String id = rs.getString("work_order_id");

                if (order.get(id)!=null){


                    String form_value = rs.getString("form_value");

                    var.put(id,form_value);

                    JSONArray objects = JSONObject.parseArray(form_value);


                    String yinZhangCompany=null;
                    String reason=null;
                    String yinZhangType=null;

                    for (Object object : objects) {

                        Map<String,Object> map=(Map) object;
                        Object o = map.get("elementLabel");
                        Object o1 = map.get("userValue");
                        if (o instanceof  String&& o1 instanceof  String){
                            String type = (String) map.get("elementLabel");
                            String userValue = (String) map.get("userValue");
                            if (type!=null && "印章/文件所属公司".equals(type)){
                                yinZhangCompany=userValue;
                            }

                            if (type!=null && "申请事由".equals(type)){
                                reason=userValue;
                            }


                            if (type!=null && "印章类型".equals(type)){
                                yinZhangType=userValue;
                            }

                        }

                    }
                    System.out.println(id+"  "+yinZhangCompany+"  "+reason+"   "+yinZhangType);
                    formValueHashMap.put(id,new FormValue(id,yinZhangCompany,reason,yinZhangType));

                }



            }

            rs.close();
            pstmt.close();


//
//            String sql3="\n" +
//                    "SELECT id,owner_name,create_time,\n" +
//                    "\n" +
//                    "case \n" +
//                    "`status`\n" +
//                    "WHEN 2 THEN '已解决待确认'\n" +
//                    "WHEN 3 THEN '已解决'\n" +
//                    "end `status` \n" +
//                    "\n" +
//                    "\n" +
//                    "   FROM t_work_order where id in (\n" +
//                    "select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null   and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
//                    ") and `status` in (2,3) and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'";


            //走完盖章就行
            String sql3="\n" +
                    "SELECT id,owner_name,create_time,\n" +
                    "\n" +
                    "case \n" +
                    "`status`\n" +
                    "WHEN 2 THEN '已解决待确认'\n" +
                    "WHEN 3 THEN '已解决'\n" +
                    "end `status` \n" +
                    "\n" +
                    "\n" +
                    "   FROM t_work_order where id in (\n" +
                    "select DISTINCT work_order_id from t_work_order_processor  WHERE processor_group_name='印章管理专员' and processor_id is not null  and status=-1  and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'\n" +
                    ")  and (create_time)>='2020-06-01' and (create_time)<='2021-05-31'";

            //工单信息
            Map<String, OrderMsg> msg  = new HashMap<>();

            PreparedStatement pstmtM = connection.prepareStatement(sql3);
            ResultSet rsM = pstmtM.executeQuery();
            while (rsM.next()) {

                String id        = rsM.getString("id");
                String ownerName    = rsM.getString("owner_name");
                String createTime = rsM.getString("create_time");
                String status       = rsM.getString("status");

                msg.put(id,new OrderMsg(id,ownerName,createTime,status));
            }

            rsM.close();
            pstmtM.close();

            ArrayList<OrderMsg> list = new ArrayList<>();


            for (Object k : ((HashMap) msg).keySet()) {

                String key =(String) k;
                if (order.get(key)!=null && formValueHashMap.get(key)!=null){

                    OrderMsg orderMsg = msg.get(key);
                    orderMsg.setCompanyYz(formValueHashMap.get(key).getYinZhangCompany());
                    orderMsg.setTypeYz(formValueHashMap.get(key).getYinZhangType());
                    orderMsg.setReason(formValueHashMap.get(key).getReason());
                    orderMsg.setSpr(order.get(key));
                    System.out.println(orderMsg);
                    list.add(orderMsg);

                }
            }


            OutputStream outputStream=new FileOutputStream("D:\\1.xlsx");


            //定义Excel正文背景颜色
            TableStyle tableStyle=new TableStyle();
            tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);

            //定义Excel正文字体大小
            Font font=new Font();
            font.setFontHeightInPoints((short) 10);

            tableStyle.setTableContentFont(font);

            Table table=new Table(0);
            table.setTableStyle(tableStyle);

//            EasyExcelUtil.writeExcelWithModel(outputStream,list,table,OrderMsg.class,ExcelTypeEnum.XLSX);
//            ExcelUtil.exportMultisheetExcel();


//JSONArray.toJSONString(list);







        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
