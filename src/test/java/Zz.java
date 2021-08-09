public class Zz {

    public static void main(String[] args) {
        String url="<img src=\"http://beta-bbs.edianzu.cn/common/images/lazy.jpg\" data-original=\"https://edianzu-oss-efamily.oss-cn-beijing.aliyuncs.com/post_detail_img/590ae5bb-fe7f-4d53-98dd-d8f8cb3913ee.jpg\" title=\"1623916491925067636.png\" alt=\"啊3.png (1).png\">";
       url= url.replaceAll("\\(","");
       url= url.replaceAll("\\)","");

        System.out.println(url);




        String a="00000";
        System.out.println(a.split(":")[0]);


    }
}
