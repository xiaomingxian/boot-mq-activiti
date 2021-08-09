package boot.spring.controller.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("filter")
public class FilterController {



    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 发送过滤消息
     */
    @RequestMapping("/sendFilter")
    public String sendFilter() throws Exception {

        for (int i = 0; i < 10; i++) {
            Message message = new Message("filterTopic",  "TagA", ("Hello filter message-" + i).getBytes());

            // 发送消息时，通过`putUserProperty`来设置消息的属性，这个属性在消费的时候会拿来写SQL语句
            message.putUserProperty("num", String.valueOf(i));

            SendResult sendResult = defaultMQProducer.send(message);

            System.out.println(String.format("SendResult status:%s, queueId:%d",
                    sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getQueueId()));
        }

        return "success";
    }



}
