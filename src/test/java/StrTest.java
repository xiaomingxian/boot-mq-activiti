import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class StrTest {

    public static void main(String[] args) {
        String s="[[{\"isRequired\":1,\"userValue\":\"客服处理结果\",\"optionValue\":[\"客服处理结果\"],\"elementLabel\":\"客服处理结果\",\"elementType\":\"radio\",\"isSupportExtended\":-1,\"elementName\":\"radio_8a142b5b0292495197e9d88e04e64688\",\"validate\":true},{\"isRequired\":-1,\"userValue\":\"c是的\",\"elementLabel\":\"备注\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_10bbc56647a941c28c265563f3839cb0\",\"validate\":true},{\"isRequired\":-1,\"userValue\":\"cs的\"," +
                "\"elementLabel\":\"问题记录\",\"elementType\":\"rich_text\",\"roleAuth\":\"客服处理\",\"isSupportExtended\":-1,\"elementName\":\"rich_text_ad5af3c0e91b4e8db6de7c90e1723d1d\",\"validate\":true}]]";

        String substring = s.substring(1);
        String substring1 = substring.substring(0,substring.length() - 1);
        System.out.println(substring1);

        HashMap<String, String> map = new HashMap<>();
        map.put("1","2");
        map.put("2","3");


        Collection<String> values = map.values();
        List<String> processRoleModels = new ArrayList<>();
        processRoleModels.addAll(values);

        System.out.println(1);


    }
}
