package boot.spring.service.impl;

import boot.spring.po.User;
import boot.spring.service.JvmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JvmServiceImpl implements JvmService {
    @Override
    public void test() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {

            try {
                Thread.sleep(500);

            }catch (Exception e){

            }

            User user = new User();
            user.setBb(new byte[1024 * 1024]);
            users.add(user);


        }


    }
}
