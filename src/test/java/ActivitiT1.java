import boot.spring.Application;
import com.alibaba.fastjson.JSONArray;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivitiT1 {
    @Autowired
private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        try {

            String pattern = ".*runoob.*";
            boolean isMatch = Pattern.matches(pattern, "aaaa");
            System.out.println(isMatch);




//            String s5="[[{\"isRequired\":-1,\"userValue\":\"\",\"elementLabel\":\"订单号\",\"elementType\":\"textarea\",\"isSupportExtended\":1,\"elementName\":\"textarea_00f6c4ed6c1e4664ba85620a42ea5328\",\"validate\":true}]]";
//            String s4="[{\"isRequired\":-1,\"userValue\":\"\",\"elementLabel\":\"备注\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_7d7bc6bed84f4ef8939ee7ca30e8b8dd\",\"validate\":true}]";
//            String s3="[{\"isRequired\":1,\"userValue\":\"1\",\"elementLabel\":\"链接ID\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_84777094cfe44efc96d68173a98fde4d\",\"validate\":true}]";
//            String s2="[{\"isRequired\":-1,\"userValue\":\"销售反馈显示器不用寄回了 直接按产品给的买断报价直接扣款就行\",\"elementLabel\":\"备注\",\"elementType\":\"textarea\",\"isSupportExtended\":-1,\"elementName\":\"textarea_86f77b3e957d47c4a17bdcf640f6d531\",\"validate\":true}]";
//            String s1="[{\"isRequired\":1,\"userValue\":\"买断价为550元/台\",\"elementLabel\":\"方案一\",\"elementType\":\"rich_text\",\"isSupportExtended\":-1,\"elementName\":\"rich_text_4ee2d0567e81492495cc04510e48f557\",\"validate\":true},{\"isRequired\":-1,\"userValue\":\"\",\"elementLabel\":\"方案二\",\"customAuth\":\"M2\",\"elementType\":\"rich_text\",\"isSupportExtended\":-1,\"elementName\":\"rich_text_c637a193cf7948459b7022fb977b9a5b\",\"validate\":true},{\"isRequired\":-1,\"fileNum\":\"-1\",\"userValue\":[],\"elementLabel\":\"方案一\",\"elementType\":\"upload_file_cust\",\"isSupportExtended\":-1,\"elementName\":\"upload_file_cust_20ea59eed0774f699ce33cb817a7241e\",\"validate\":true},{\"isRequired\":-1,\"fileNum\":\"-1\",\"userValue\":[],\"elementLabel\":\"方案二\",\"customAuth\":\"M2\",\"elementType\":\"upload_file_cust\",\"isSupportExtended\":-1,\"elementName\":\"upload_file_cust_f0773ebf5284400c82a787b70e199c9c\",\"validate\":true}]";
//            String s="[{\"isRequired\":1,\"userValue\":\"不需要产品报价\",\"optionValue\":[\"需要产品报价\",\"不需要产品报价\"],\"elementLabel\":\"销售发起\",\"controlId\":16722,\"elementType\":\"radio\",\"isSupportExtended\":-1,\"elementName\":\"new_property_11\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"201\",\"elementLabel\":\"系统买断价\",\"inputTips\":\"不需要产品报价请填写系统买断价，需要产品报价请填“无”\",\"elementType\":\"text\",\"isSupportExtended\":-1,\"elementName\":\"text_5638dfdb67774a1b9ee40bfab59165cc\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"10167442$customer东莞火蚁跨境供应链有限公司\",\"elementLabel\":\"客户名称\",\"inputType\":\"2\",\"elementType\":\"linked_customer\",\"isSupportExtended\":-1,\"customerName\":\"东莞火蚁跨境供应链有限公司\",\"elementName\":\"linked_customer_e8784e962c2a48ee8a93cace2090541d\",\"validate\":true},{\"elementsJsonStr\":[{\"isRequired\":1,\"userValue\":\"2021011210167442010 \",\"errorTips\":\"\",\"elementLabel\":\"订单号\",\"regexName\":\"orderId\",\"inputTips\":\"仅可输入一个订单号，需输入多个请新增卡片\",\"elementType\":\"text\",\"elementName\":\"text_e4edd413d8b54d6e8242a985114cc6b3\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"CN0F90WNFCC0089MCPVB\",\"errorTips\":\"\",\"elementLabel\":\"设备序列号\",\"inputTips\":\"请输入设备序列号，多个序列号请逗号分隔，序列号可通过CRM中的订单详情查询设备序列号\",\"elementType\":\"textarea\",\"elementName\":\"textarea_4d19db85417341e19eba00e52505cd6b\",\"validate\":true}],\"elementLabel\":\"订单号序列号\",\"elementNum\":2,\"description\":\"\",\"elementType\":\"22\",\"componentSetId\":\"22_d59ef9f816bf402491b2f21542825257\",\"isSupportExtended\":1,\"elementName\":\"订单号序列号\",\"validate\":true},{\"isRequired\":1,\"userValue\":\"1\",\"elementLabel\":\"变更台数\",\"regexName\":\"digits\",\"elementType\":\"text\",\"isSupportExtended\":-1,\"elementName\":\"text_eb86d64f70b644f98a3ee77c5c234a5b\",\"validate\":true},{\"isRequired\":-1,\"fileNum\":\"-1\",\"userValue\":[],\"elementLabel\":\"附件\",\"elementType\":\"upload_file\",\"isSupportExtended\":-1,\"elementName\":\"upload_file_27b1a40dd09d44ebbe710ed1c946659c\",\"validate\":true}]";
//            JSONArray.parseArray(s5);

            String s="guoshiying@edianzu.com,sujianjian@edianzu.com,gaobo@edianyun.com;sunpeng@edianyun.com,sunpeng@edianzu.com,xianxiaoming@edianyun.com";


            String[] split = s.split(",");
            for (String s1 : split) {
                System.out.println(s1);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @Test
    public void  rep(){
        String name="aaa.bpmn";
        repositoryService.createDeployment()
                .addClasspathResource("D:\\xxm\\edy\\myActiviti\\src\\main\\resources\\bpmn\\orderStartDateChange_SALEP.bpmn").deploy();
    }


    @Test
    public void  start(){
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("epandian_pay-copy");

        System.out.println(leave.getProcessInstanceId());
    }


    /**
     * 重复认领会报错:Task '10004' is already claimed by someone else.
     */
    @Test
    public void  claim(){
//        taskService.setAssignee("10004","测试认领人");
//        taskService.claim("25004","测试认领人1");
        taskService.setOwner("2504","测试认领人2");
    }


    @Test
    public void  doTask(){
        Map<String,Object> var =new HashMap<>();

        var.put("new_property_1","同意");
        taskService.complete("15004",var);
    }

    @Test
    public void  do3(){
//        687105
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("epandian_pay-copy");
        String processInstanceId = leave.getProcessInstanceId();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        String taskId = task.getId();
        taskService.setOwner(taskId,"测试认领人2");

        System.out.println(taskId);

    }




}
