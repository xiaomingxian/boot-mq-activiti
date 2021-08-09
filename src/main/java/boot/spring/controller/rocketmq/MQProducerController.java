package boot.spring.controller.rocketmq;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: lockie
 * @Date: 2020/4/21 11:17
 * @Description:
 */
@RestController
@RequestMapping("/mqProducer")
public class MQProducerController {
    /**
     * 1 同步消息
     * 2 异步消息
     * 3 单向消息
     * @param args
     */


    public static void main(String[] args) {
        Long i=1000L;
        Long i2=null;
        System.out.println(i.equals(i2));
    }


    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerController.class);

    @Autowired(required = false)
    DefaultMQProducer defaultMQProducer;


    /**
     * 发送简单的MQ消息  同步消息
     *
     * @param msg
     * @return
     */
    @GetMapping("/send")
    public Object send(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (StringUtils.isEmpty(msg)) {
            return "没消息";
        }
        LOGGER.info("发送MQ消息内容：" + msg);
        Message sendMsg = new Message("TestTopic", "TestTag", msg.getBytes());
        // 默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        LOGGER.info("消息发送响应：" + sendResult.toString());
        return sendResult;
    }


    /**
     * 异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
     * @return
     * @throws Exception
     */
    @GetMapping("syncSend")
    public String syncSend() throws  Exception{
        String topic  = "TestTopic";
        String tags = "TestTag";

        String msg=" test MSg syncSend"+UUID.randomUUID();
        LOGGER.info("发送MQ消息内容：" + msg);
        Message message = new Message(topic, tags, UUID.randomUUID().toString(), msg.getBytes());

        // 默认3秒超时 ,异步发送消息
        defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                LOGGER.info("发送MQ消息成功：{}" ,sendResult);
                System.out.println(sendResult);
                //保存消费消息
                //RocketmqMessageLog rocketmqMessageLog = new RocketmqMessageLog();
                //rocketmqMessageLog.setId(uidGenService.getUid());
                //rocketmqMessageLog.setMsgBody(msg);
                //rocketmqMessageLog.setMsgId(sendResult.getMsgId());
                //rocketmqMessageLog.setQueueId(sendResult.getMessageQueue().getQueueId());
                //rocketmqMessageLog.setTopic(topic);
                //rocketmqMessageLog.setTags(tags);
                //rocketmqMessageLogMapper.insert(rocketmqMessageLog);
            }

            @Override
            public void onException(Throwable e) {
                LOGGER.info("发送MQ消息异常：",e);
                System.out.println(e.getMessage());
            }
        });
        LOGGER.info("消息发送完成" );


        return "success";
    }


    @RequestMapping("/sendOneWay")
    public String sendOneWay(String msg) throws Exception {
        LOGGER.info("发送MQ消息内容：" + "单向消息:"+msg);
        Message sendMsg = new Message("TestTopic", "TestTag", UUID.randomUUID().toString(), msg.getBytes());
        // 发送消息
        defaultMQProducer.sendOneway(sendMsg);
        return "SUCCESS";
    }



}