import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.catalina.Manager;
import sun.net.www.content.image.png;

import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T3 {


    static String reg = "([sS]*?)";
    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String str ="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><br/>兔子<br/><p style=\"text-align:center;\"><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/b5041fba-023b-410d-b50c-c55fe0fb88d2.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/b5041fba-023b-410d-b50c-c55fe0fb88d2.jpg\"></p><br/><p style=\"text-align:start;\">\u200B<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/67d022a3-6d44-4f03-b35e-9bcd13c90cbb.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/67d022a3-6d44-4f03-b35e-9bcd13c90cbb.jpg\"></p><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/782e15da-959d-4c40-9cf8-1365abb395da.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/782e15da-959d-4c40-9cf8-1365abb395da.jpg\"><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8549c2c-d733-49bc-b4ca-d9c81bf9461b.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8549c2c-d733-49bc-b4ca-d9c81bf9461b.jpg\"><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/70cace0c-f2e9-4dae-8e71-3062833043fc.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/70cace0c-f2e9-4dae-8e71-3062833043fc.jpg\"><br/>哈哈";
////        Pattern p = Pattern.compile(reg, Pattern.MULTILINE);
//        Pattern p = Pattern.compile("<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*>", Pattern.MULTILINE);
//        str = str.replace("&nbsp;", "");
//        Matcher m = p.matcher(str);
//        while(m.find()) {
//            String data = m.group(1).trim();
//            if(!"".equals(data)) {
//                System.out.println(data);
//            }
//        }

//        sss();

//        pTagTest();
//        System.out.println("pP".replaceAll("P","***"));

    }

    private static void pTagTest() {

String url ="<p>p</p><p>艰难的时刻你分手多久啊剋加拿大队今年开始</p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8730f79-b371-49bb-93a8-259be0da5752.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8730f79-b371-49bb-93a8-259be0da5752.jpg\" style=\"\" title=\"1623844558539037433.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/14eab546-0e40-4afc-b2c4-8d58c511d159.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/14eab546-0e40-4afc-b2c4-8d58c511d159.jpg\" style=\"\" title=\"1623844558452040870.png\"/></p><p><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/77c6488f-1e90-4abf-b16e-2e6e7a227980.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/77c6488f-1e90-4abf-b16e-2e6e7a227980.jpg\" style=\"\" title=\"1623844558492003994.png\"/></p><p><br/></p>";


        String[] split = url.split("<p>");


    }


    public static void  sss(){


        String htmlStr ="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><br/>兔子<br/><p style=\"text-align:center;\"><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/b5041fba-023b-410d-b50c-c55fe0fb88d2.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/b5041fba-023b-410d-b50c-c55fe0fb88d2.jpg\"></p><br/><p style=\"text-align:start;\">\u200B<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/67d022a3-6d44-4f03-b35e-9bcd13c90cbb.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/67d022a3-6d44-4f03-b35e-9bcd13c90cbb.jpg\"></p><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/782e15da-959d-4c40-9cf8-1365abb395da.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/782e15da-959d-4c40-9cf8-1365abb395da.jpg\"><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8549c2c-d733-49bc-b4ca-d9c81bf9461b.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/d8549c2c-d733-49bc-b4ca-d9c81bf9461b.jpg\"><br/><img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/70cace0c-f2e9-4dae-8e71-3062833043fc.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/70cace0c-f2e9-4dae-8e71-3062833043fc.jpg\"><br/>哈哈";


        String textStr ="";

        java.util.regex.Pattern p_script;

        java.util.regex.Matcher m_script;

        java.util.regex.Pattern p_style;

        java.util.regex.Matcher m_style;

        java.util.regex.Pattern p_html;

        java.util.regex.Matcher m_html;

        try{

            String regEx_script = "]*?>[\\s\\S]*?"; //定义script的正则表达式{或

            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或

            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);

            m_script = p_script.matcher(htmlStr);

            htmlStr = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);

            m_style = p_style.matcher(htmlStr);

            htmlStr = m_style.replaceAll(""); //过滤style标签

            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);

            m_html = p_html.matcher(htmlStr);

            htmlStr = m_html.replaceAll(""); //过滤html标签

            textStr = htmlStr;


            System.out.println(textStr);
        }catch(Exception e){
e.printStackTrace();
        }








    }
}
