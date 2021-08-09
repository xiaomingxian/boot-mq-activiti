import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T2 {
    public static void main(String[] args) {

//        /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/
//        String pattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
//        boolean isMatch = Pattern.matches(pattern, "run_ oob");
//        System.out.println(isMatch);


        //将字符串拆开


        String url = "<p>两次的吗就看电视剧看到过你就看你的时光</p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/76be1994-cf8c-4c9a-baf6-f3116020cb44.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/76be1994-cf8c-4c9a-baf6-f3116020cb44.jpg\" style=\"\" title=\"1623309873192074055.jpg\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/c648b9be-a431-46d0-81fb-af755cd3c650.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/c648b9be-a431-46d0-81fb-af755cd3c650.jpg\" style=\"\" title=\"1623309873148014009.jpg\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/7c339e11-c848-4b03-8bd2-65c75fd24b25.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/7c339e11-c848-4b03-8bd2-65c75fd24b25.jpg\" style=\"\" title=\"1623309873135011156.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/801768d6-9460-4062-9216-cf9582b18031.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/801768d6-9460-4062-9216-cf9582b18031.jpg\" style=\"\" title=\"1623309874025005813.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/8badfc09-b9bd-43dc-946d-291cb11e8ba7.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/8badfc09-b9bd-43dc-946d-291cb11e8ba7.jpg\" style=\"\" title=\"1623309874388017988.jpg\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/3a0c5fde-a28b-4e09-9ab0-eb4ea4766ed5.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/3a0c5fde-a28b-4e09-9ab0-eb4ea4766ed5.jpg\" style=\"\" title=\"1623309874333098519.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/3ec9a7f1-0239-4c99-9d8a-747495a68d94.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/3ec9a7f1-0239-4c99-9d8a-747495a68d94.jpg\" style=\"\" title=\"1623309875053081170.jpg\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/4f9c9d95-ba68-49ea-9701-0a085e7e70e2.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/4f9c9d95-ba68-49ea-9701-0a085e7e70e2.jpg\" style=\"\" title=\"1623309875243092569.jpg\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/51bec439-13a9-406f-8b9f-8bcd76a790fb.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/51bec439-13a9-406f-8b9f-8bcd76a790fb.jpg\" style=\"\" title=\"1623309875106030663.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/dbbab593-3b91-43ed-be26-10be5cb485ba.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/dbbab593-3b91-43ed-be26-10be5cb485ba.jpg\" style=\"\" title=\"1623309875933097085.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/a0329ead-cb18-4d2b-875b-789a053b2ebc.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/a0329ead-cb18-4d2b-875b-789a053b2ebc.jpg\" style=\"\" title=\"1623309875729022415.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/18e5b55f-423f-4c04-9405-a98482b66dd2.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/18e5b55f-423f-4c04-9405-a98482b66dd2.jpg\" style=\"\" title=\"1623309875868053501.png\"/></p><p><br/></p>";

//        url = "<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/cea1f27d-7a44-4750-9a5d-0cccc65f1ce0.jpg\" alt=\"(null)\"><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/95ce0833-d5c9-4598-a864-4f73fc111d6b.jpg\" alt=\"(null)\">匿名1";
//        url="<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/cea1f27d-7a44-4750-9a5d-0cccc65f1ce0.jpg\" alt=\"a.jpg\"><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/95ce0833-d5c9-4598-a864-4f73fc111d6b.jpg\" alt=\"a.jpg\">匿名1";

//        System.out.println(url.contains("<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/cea1f27d-7a44-4750-9a5d-0cccc65f1ce0.jpg\" alt=\"(null)\">"));

        url="<p>大煞风景还不敢放声大哭锦1鲤</p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/a47ca391-0cb9-478a-a1b5-81983463c210.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/a47ca391-0cb9-478a-a1b5-81983463c210.jpg\" style=\"\" title=\"1623751440512075612.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/0bb3652e-15cb-49de-bc1f-f951e90f1c62.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/0bb3652e-15cb-49de-bc1f-f951e90f1c62.jpg\" style=\"\" title=\"1623751440753070460.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/79639b67-7bd9-40f7-bfc7-748a69005406.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/79639b67-7bd9-40f7-bfc7-748a69005406.jpg\" style=\"\" title=\"1623751440805074343.png\"/></p><p><br/></p>";

        List<String> pics = new ArrayList<>(); // 因文件可能有多张图片，故用集合来存储结果
        Pattern compile = Pattern.compile("<img.*?>", Pattern.CASE_INSENSITIVE);

        Matcher matcher = compile.matcher(url); // string：后台返的内容，图片就是从中提取的
        Map<String, String> oldAndNew = new HashMap<>();

        Pattern pattern2 = Pattern.compile("src=(\"|'| |)([\\S]{1,}?|[/]{1,})([/]{1,})(.+?)([/]{1,})(.+?)\\.(png|jpg|jpeg)(\"|'| |/>)");


        Pattern pattern3 = Pattern.compile("data-original=(\"|'| |)([\\S]{1,}?|[/]{1,})([/]{1,})(.+?)([/]{1,})(.+?)\\.(png|jpg|jpeg)(\"|'| |/>)");

        while (matcher.find()) {
            String img = matcher.group();
            pics.add(img);
            Matcher matcher2 = pattern2.matcher(img);
            Matcher matcher3 = pattern3.matcher(img);
            System.out.println(img);

            String imgUrl = "";
            while (matcher2.find()) {
                imgUrl = matcher2.group(2) + matcher2.group(3) + matcher2.group(4) + matcher2.group(5) + matcher2.group(6) + "." + matcher2.group(7);
            }


            String real = "";
            while (matcher3.find()) {
                real = matcher3.group(2) + matcher3.group(3) + matcher3.group(4) + matcher3.group(5) + matcher3.group(6) + "." + matcher3.group(7);
            }


            String imgNew = img.replaceAll(imgUrl, real);

            oldAndNew.put(img, imgNew);

        }


        System.out.println("----------------------");
        String a;
        for (String old : oldAndNew.keySet()) {
            String newUrl = oldAndNew.get(old);
            System.out.println("old:" + old + "\nnew :" + newUrl);
            url = url.replaceAll(old, newUrl);
        }


        System.out.println(url);

//        System.out.println("===========================");
//
//        String uuuuu ="<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/cea1f27d-7a44-4750-9a5d-0cccc65f1ce0.jpg\" alt=\"(null)\"><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/95ce0833-d5c9-4598-a864-4f73fc111d6b.jpg\" alt=\"(null)\">匿名1";
//        String ooooo = "<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/95ce0833-d5c9-4598-a864-4f73fc111d6b.jpg\" alt=\"(null)\">";
//
//        String nnnnn = "<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/95ce0833-d5c9-4598-a864-4f73fc111d6b.jpg\" alt=\"(null)\">";
//        System.out.println(uuuuu.contains(ooooo));
//
//        System.out.println(uuuuu.replaceAll(ooooo,"---"));
//
//        System.out.println("===========================))))))))))))))))))))");
//        String oooo1="alt=\"null\"1";
//        System.out.println(oooo1.replaceAll(oooo1,"*"));

    }
}
