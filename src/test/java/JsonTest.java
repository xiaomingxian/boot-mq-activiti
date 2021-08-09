import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {


    public static void main(String[] args) throws Exception {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connectionLocal = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false", "root", "root");

//
        String sqlP = "SELECT id,wid,json FROM gaizhang      where type='扫描件,邮寄'   ";
//        String sqlP = "SELECT id,wid,json FROM gaizhang_copy      where type='合'  ";
//        String sqlP = "SELECT id,wid,json FROM gaizhang     ";

        PreparedStatement pstmtp = connectionLocal.prepareStatement(sqlP);
        ResultSet rsp = pstmtp.executeQuery();
        while (rsp.next()) {
            String id = rsp.getString("id");
            String wid = rsp.getString("wid");
            String jsonStr = rsp.getString("json");

            JSONArray objects = JSONObject.parseArray(jsonStr);

            HashMap<String, String> resMap = new HashMap<>();
            resMap.put("yinZhangCompany", null);
            resMap.put("reason", null);
            resMap.put("yinZhangType", null);
            resMap.put("wid", wid);


            for (Object object : objects) {

                Map<String, Object> map = (Map) object;
                //递归找
                for (String key : map.keySet()) {

                    diguiFind(map, key, resMap);


                }


            }
//            String sqlLocal = "UPDATE gaizhang set company =?,`type`=?,reason=? where id=?";
//            PreparedStatement preparedStatement = connectionLocal.prepareStatement(sqlLocal);
//            preparedStatement.setString(1,  resMap.get("yinZhangCompany"));
//            preparedStatement.setString(2,  resMap.get("yinZhangType"));
//            preparedStatement.setString(3, resMap.get("reason"));
//            preparedStatement.setString(4, id);
//            preparedStatement.execute();
////
//            preparedStatement.close();
            System.out.println(wid + "   " + resMap + "   " + jsonStr);


        }
        pstmtp.close();
        connectionLocal.close();


    }

    private static void b(String a) {
        a = "b";
    }

    private static void diguiFind(Map<String, Object> map, String key, Map<String, String> resMap) {
        String yinZhangCompany = resMap.get("yinZhangCompany");
        String yinZhangType = resMap.get("yinZhangType");
        String reason = resMap.get("reason");

//        System.out.println(yinZhangCompany+"  "+yinZhangType+"  "+reason);

        if (yinZhangCompany != null && yinZhangType != null && reason != null) {
            return;
        }

        if (map.get(key) instanceof List) {
            List listInner = (List) map.get(key);


            if (listInner.size() > 0 && listInner.get(0) != null) {
                if (listInner.get(0) instanceof Map) {
                    List<Map<String, Object>> list = (List) map.get(key);
                    for (Map<String, Object> jsonMap : list) {

                        for (String keyInner : jsonMap.keySet()) {
                            diguiFind(jsonMap, keyInner, resMap);
                        }

                    }
                } else {

                    if (map.get("elementLabel") != null && map.get("userValue") != null) {
                        String keyEl = map.get("elementLabel").toString();
                        String value = map.get("userValue").toString();
                        //当递归到没有元素或者  三个变量都有值时退出
                        setVal(keyEl, value, resMap, map);

                    }
//                    String value = listInner.get(0).toString();
//                    setVal(key, value, yinZhangCompany, reason, yinZhangType);
                }


            }


        } else {
            if (map.get("elementLabel") != null && map.get("userValue") != null) {
                String keyEl = map.get("elementLabel").toString();
                String value = map.get("userValue").toString();
                //当递归到没有元素或者  三个变量都有值时退出
                setVal(keyEl, value, resMap, map);

            }


        }


    }

    private static void setVal(String key, String value, Map<String, String> resMap, Map<String, Object> map) {


        if (value != null) {
            value = value.replaceAll("\\[\"", "")
                    .replaceAll("\"]", "")
                    .replaceAll("\"", "")
            ;


        }

        String yinZhangCompany = resMap.get("yinZhangCompany");
        String yinZhangType = resMap.get("yinZhangType");
        String reason = resMap.get("reason");


        if ((key.contains("文件所属公司") || key.equals("印章公司")) && yinZhangCompany == null) {
            resMap.put("yinZhangCompany", value);


        }

        if ((key.contains("申请事由") || key.equals("查看原因")) && reason == null) {
            resMap.put("reason", value);


        }

//        (key).equals("盖章类型") ||
        if (key != null &&
                (
                        (key).equals("印章类型")
//                                || (key).equals("合同类型")
                                || (key).equals("用印类别")
                                || (key).equals("是否盖章（公章）")
                                || (key).equals("申请执照")
//                                || (key).equals("文件类型")
                                || (key).equals("申请")

                ) && yinZhangType == null) {


            //打印附件盖章与


//            if ((key).equals("用印类别") && "1,2,3,4".contains(value)) {
//                    String index =value;
//                Object selectRadioList = map.get("selectRadioList");
//                if (selectRadioList instanceof List) {
//                    List<Map<String, Object>> list = (List) selectRadioList;
//                    if (value.equals(list.size() + "")) {
//
//                        value = list.get(Integer.parseInt(value) - 1).get("label").toString();
////                        Integer indexInner = (Integer) list.get(Integer.parseInt(index) - 1).get("val");
////                        List<Map<String,Object>> compentList =(List<Map<String, Object>>) list.get(Integer.parseInt(index) - 1).get("compentList");
////                        value = compentList.get(indexInner-1).get("userValue").toString();
//
//
//                        if (value==null)value= list.get(Integer.parseInt(value) - 1).get("userValue").toString();
//                        resMap.put("yinZhangType", value);
//                    }
//
//
//                }
//
//            }

            //处理鲜章
            if ((key).equals("用印类别") && "1,2,3,4".contains(value)) {
                    String index =value;
                Object selectRadioList = map.get("selectRadioList");
                if (selectRadioList instanceof List) {
                    List<Map<String, Object>> list = (List) selectRadioList;
                    if (value.equals(list.size() + "")) {

                        value = list.get(Integer.parseInt(value) - 1).get("label").toString();
                        Integer indexInner = (Integer) list.get(Integer.parseInt(index) - 1).get("val");
                        List<Map<String,Object>> compentList =(List<Map<String, Object>>) list.get(Integer.parseInt(index) - 1).get("compentList");
//                        System.out.println(resMap+"        "+map);
                        if (compentList.size()<indexInner){
                            value=value;
                        }else {

                            value = compentList.get(indexInner-1).get("userValue").toString();

                        }


//                        if (value==null)value= list.get(Integer.parseInt(value) - 1).get("userValue").toString();
                        resMap.put("yinZhangType", value);
                    }


                }

            }
            if ((key).equals("是否盖章（公章）") && value.equals("是")) {
                resMap.put("yinZhangType", "公章");

            } else if ((key).equals("申请执照")) {

                resMap.put("yinZhangType", value);
            } else if ((key).equals("文件类型")) {
                value = value.contains("公章") ? "公章" : value;

                resMap.put("yinZhangType", value);
            } else if (value.contains("章")) {

                resMap.put("yinZhangType", value);

            }

            if (!value.contains("章") || "1,2,3,4".contains(value)) {

                Object selectRadioList = resMap.get("selectRadioList");
                if (selectRadioList instanceof List) {
                    List<Map<String, Object>> list = (List) selectRadioList;
                    if (value.equals(list.size() + "")) {
                        value = list.get(Integer.parseInt(value)).get("label").toString();
                        resMap.put("yinZhangType", value);
                    }


                }

            }


        }
    }


}
