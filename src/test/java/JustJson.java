import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JustJson {

    public static void main(String[] args) {
        String ElemenntLable="离职人员姓名";


//        String jsonStr="[{\"isRequired\":1,\"selectedLabel\":\"董宇 - 研发中心\",\"isShowPerformance\":\"1\",\"isSupportExtended\":-1,\"customerName\":\"董宇 - 研发中心\",\"elementName\":\"person_cust_dbb04d6dba4d4c0bbbfeed6bca43c256\",\"userValue\":\"13757$personCust董宇 - 研发中心\",\"isDepentdent\":\"1\",\"elementLabel\":\"离职人员姓名\",\"inputType\":\"1\",\"selectedValue\":\"13757\",\"elementType\":\"person_cust\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"2020-12-07\",\"elementLabel\":\"离职日期\",\"elementType\":\"date_picker\",\"isSupportExtended\":-1,\"isSupportTime\":\"-1\",\"elementName\":\"date_picker_8a3ba16ebdc447ddae9f435ea7c3111b\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"离职\",\"elementLabel\":\"离职原因\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_3a305ec0cfc54f6e8cdeda95ea6f7116\",\"validate\":true}]";
        String jsonStr="[{\"isRequired\":1,\"userValue\":\"14576$personCust程倩 - 整修制造中心\",\"isDepentdent\":\"1\",\"elementLabel\":\"离职人员姓名\",\"isShowPerformance\":\"1\",\"inputType\":\"1\",\"elementType\":\"person_cust\",\"isSupportExtended\":-1,\"customerName\":\"程倩 - 整修制造中心\",\"elementName\":\"person_cust_ad74122a9a444a15b116810c64db716a\",\"validate\":true}, {\"isRequired\":1,\"userValue\":\"2020-03-03\",\"elementLabel\":\"离职日期\",\"elementType\":\"date_picker\",\"isSupportExtended\":-1,\"isSupportTime\":\"-1\",\"elementName\":\"date_picker_846e92d2d176424a9df36119d1cca627\",\"validate\":true}, {\"isRequired\":1,\"userValue\":\"个人愿意\",\"elementLabel\":\"离职原因\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_fc0161f43e414ef8a52bc72ae53bb60d\",\"validate\":true}]";
        JSONArray objects = JSONObject.parseArray(jsonStr);

        HashMap<String, String> resMap = new HashMap<>();
        resMap.put(ElemenntLable, null);


        for (Object object : objects) {

            Map<String, Object> map = (Map) object;
            //递归找
            for (String key : map.keySet()) {

                diguiFind(map, key, resMap);


            }


        }




    }

    private static void diguiFind(Map<String, Object> map, String key, Map<String, String> resMap) {

        Collection<String> values = resMap.values();
        boolean allHavaVal=true;
        for (String value : values) {
            if (value==null){
                allHavaVal=false;
                break;
            }
        }
        //如果所有的都有值了 就退出
        if (allHavaVal) {
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
        System.out.println(key);




    }



}
