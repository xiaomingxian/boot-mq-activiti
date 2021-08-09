package boot.spring.controller.rocketmq;

import boot.spring.util.ListSplitter;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("batch")
@Slf4j
public class BatchMqController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     * 发送批量消息
     */
    @RequestMapping("/sendBatch")
    public String sendBatch() throws Exception {

        // 创建批量消息
        List<Message> messageList = new ArrayList<>();
        Message message1 = new Message("batchTopic", "batchTag", ("Hello Batch message-1").getBytes());
        Message message2 = new Message("batchTopic", "batchTag", ("Hello Batch message-2").getBytes());
        Message message3 = new Message("batchTopic", "batchTag", ("Hello Batch message-3").getBytes());
        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);

        log.info("发送MQ消息内容：" + JSONObject.toJSONString(messageList));

        // 发送消息
        SendResult sendResult = defaultMQProducer.send(messageList);
        // 打印结果
        System.out.println(String.format("SendResult status:%s, queueId:%d",
                sendResult.getSendStatus(),
                sendResult.getMessageQueue().getQueueId()));
        return "SUCCESS";
    }

    /**
     * 发送批量消息
     */
    @RequestMapping("/sendBatchThan4M")
    public String sendBatchThan4M() throws Exception {

        // 创建批量消息
        List<Message> messageList = new ArrayList<>();
        Message message1 = new Message("batchTopic", "batchTag", ("Hello Batch message-1").getBytes());
        Message message2 = new Message("batchTopic", "batchTag", ("Hello Batch message-2").getBytes());
        Message message3 = new Message("batchTopic", "batchTag", ("Hello Batch message-3").getBytes());
        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);

        log.info("发送MQ消息内容：" + JSONObject.toJSONString(messageList));

        //发送批量消息：把大的消息分裂成若干个小的消息
        ListSplitter splitter = new ListSplitter(messageList);
        while (splitter.hasNext()) {
            try {
                List<Message> listItem = splitter.next();
                SendResult result = defaultMQProducer.send(listItem);
                System.out.println(String.format("SendResult status:%s, queueId:%d",
                        result.getSendStatus(),
                        result.getMessageQueue().getQueueId()));
            } catch (Exception e) {
                e.printStackTrace();
                //处理error
            }
        }

        return "success";
    }



}
