package boot.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 第一次加载到Map中 如果有增删 就重新加载
 */
@Component
public class SensitiveWord {
    private  StringBuilder replaceAll;//初始化
    private  String encoding = "UTF-8";
    private  String replceStr = "*";
    private  int replceSize = 500;
    private  String fileName = "CensorWords.txt";
    private  List<String> arrayList;
    public  Set<String> sensitiveWordSet;//包含的敏感词列表，过滤掉重复项
    public  List<String> sensitiveWordList;//包含的敏感词列表，包括重复项，统计次数

    public SensitiveWord(String fileName) {
        this.fileName = fileName;
    }

    @Value("${censorwords_path}")
    private  String path;

    /**
     * @param replceStr  敏感词被转换的字符
     * @param replceSize 初始转义容量
     */
    public SensitiveWord(String replceStr, int replceSize) {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }

    public SensitiveWord() {
    }


    /**
     * 增加敏感词
     */
    public  boolean add(String str) {
        List<String> list = new ArrayList<String>();
        list.add(str);
        return addList(list);
    }

    /**
     * 批量增加敏感词
     */
    public  boolean addList(List<String> strs) {
        FileOutputStream out = null;
        OutputStreamWriter outWriter = null;
        BufferedWriter bufWrite = null;

        try {
            out = new FileOutputStream(path,true);
            outWriter = new OutputStreamWriter(out, "UTF-8");
            bufWrite = new BufferedWriter(outWriter);

            for (String str : strs) {
                bufWrite.write("\r\n"+str );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufWrite.close();
                outWriter.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        InitializationWork();
        return true;
    }

    /**
     * 删除敏感词
     */
    public boolean del(String str) {
        InitializationWork();
        return true;
    }

    /**
     * 批量删除敏感词
     */
    public boolean delList(List<String> strs) {


        InitializationWork();
        return true;
    }

    /**
     * @param str 将要被过滤信息
     * @return 过滤后的信息
     */
    public  String filterInfo(String str) {
        if (arrayList == null || arrayList.size() == 0) {
            //初始化
            InitializationWork();
        }
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList = new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        for (int x = 0; x < arrayList.size(); x++) {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1; ) {
                //System.out.println("###replace="+temp);
                findIndexSize = start + temp.length();//从已找到的后面开始找
                Integer mapStart = hash.get(start);//起始位置  
                if (mapStart == null || (mapStart != null && findIndexSize > mapStart))//满足1个，即可更新map
                {
                    hash.put(start, findIndexSize);
                    System.out.println("###敏感词：" + buffer.substring(start, findIndexSize));
                }
            }
        }
        Collection<Integer> values = hash.keySet();
        for (Integer startIndex : values) {
            Integer endIndex = hash.get(startIndex);
            //获取敏感词，并加入列表，用来统计数量
            String sensitive = buffer.substring(startIndex, endIndex);
            //System.out.println("###敏感词："+sensitive);
            if (!sensitive.contains("*")) {//添加敏感词到集合
                sensitiveWordSet.add(sensitive);
                sensitiveWordList.add(sensitive);
            }
            buffer.replace(startIndex, endIndex, replaceAll.substring(0, endIndex - startIndex));
        }
        hash.clear();
        return buffer.toString();
    }

    /**
     * 初始化敏感词库
     */
    public  void InitializationWork() {
        replaceAll = new StringBuilder(replceSize);
        for (int x = 0; x < replceSize; x++) {
            replaceAll.append(replceStr);
        }
        //加载词库  
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
//            ClassPathResource classPathResource = new ClassPathResource(path);
//            read = new InputStreamReader(classPathResource.getInputStream(), encoding);
            read = new InputStreamReader(new FileInputStream(path), encoding);
//            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName),encoding);
            bufferedReader = new BufferedReader(read);
            for (String txt = null; (txt = bufferedReader.readLine()) != null; ) {

                if (!arrayList.contains(txt)&& !"".equals(txt))
                    arrayList.add(txt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;
    }

    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }

    public String getReplceStr() {
        return replceStr;
    }

    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }

    public int getReplceSize() {
        return replceSize;
    }

    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }


    public static void main(String[] args) {
        long startNumer = System.currentTimeMillis();
        SensitiveWord sw = new SensitiveWord("CensorWords.txt");
        sw.InitializationWork();
        //System.out.println("敏感词的数量：" + arrayList.size());
        String str = "哈哈哈哈人流法轮功太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "关, 人, 流, 电, 发, 情, 太, 限, 法轮功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("被检测字符串长度:" + str.length());
        str = sw.filterInfo(str);
        long endNumber = System.currentTimeMillis();
        //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordSet.size() + "。包含：" + sensitiveWordSet);
        //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordList.size() + "。包含：" + sensitiveWordList);
        System.out.println("总共耗时:" + (endNumber - startNumer) + "ms");
        System.out.println("替换后的字符串为:\n" + str);
    }
}  

