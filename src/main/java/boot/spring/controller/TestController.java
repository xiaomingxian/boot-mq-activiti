package boot.spring.controller;

import boot.spring.util.SensitiveWord;
import net.bytebuddy.asm.Advice;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("t")
@RestController
public class TestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private SensitiveWord sensitiveWord;

    @GetMapping("search")
    public  String  search(String str){


        return sensitiveWord.filterInfo(str);
    }


    @GetMapping("add")
    public  boolean  add(String str){


        return sensitiveWord.add(str);
    }



    @GetMapping("rep")
    public String rep(String name) {
        try {
            repositoryService.createDeployment()
                    .addClasspathResource("bpmn/" + name).deploy();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @GetMapping("start")
    public String start() {

        Map<String, Object> var = new HashMap<>();
        var.put("deptleader", "tom");
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("leave", var);
        System.out.println(leave.getProcessInstanceId());


        return "success:" + leave.getProcessInstanceId();
    }


    @GetMapping("complate")
    public String complate(String tid) {
        try {
            taskService.complete(tid);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }


    }


}
