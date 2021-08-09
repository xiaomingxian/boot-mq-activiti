package boot.spring.controller.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("dely")
public class DelyMqController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;


    /**
     * 发送延时消息
     * @param msg
     * @return
     */
    @RequestMapping("/sendDelay")
    public String sendDelay(String msg) throws Exception {
        if (StringUtils.isEmpty(msg)) {
            return "EMPTY";
        }
        log.info("发送MQ消息内容：" + msg);

        Message message = new Message("delayTopic", "TestTag", UUID.randomUUID().toString(), msg.getBytes());

        // 设置延时等级2,这个消息将在5s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
        message.setDelayTimeLevel(2);
        // 发送消息
        SendResult sendResult = defaultMQProducer.send(message);
        // 打印结果
        System.out.println(String.format("SendResult status:%s, queueId:%d",
                sendResult.getSendStatus(),
                sendResult.getMessageQueue().getQueueId()));
        return "SUCCESS";
    }



}




