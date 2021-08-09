package boot.spring.controller;

import boot.spring.service.JvmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("jvm")
@RestController
public class JvmController {

    @Autowired
    private JvmService jvmService;


    @GetMapping("test")
    public void test(){
        jvmService.test();


    }





}
