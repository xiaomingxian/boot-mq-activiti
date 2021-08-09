package boot.spring.controller.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("tran")
@RestController
@Slf4j
public class TranMqController {


    /**
     * 发送事务消息1
     */
    @RequestMapping("/sendTransaction")
    public String sendTransaction() throws Exception {

        // 创建 TransactionMQProducer 实例，并设置生产者组名
        TransactionMQProducer producer = new TransactionMQProducer("transactionGroup");
        // 设置 NameServer 地址
        producer.setNamesrvAddr("49.234.25.12:9876");
        // 设置 Tags
        String[] tags = {"TagA", "TagB", "TagC"};

        // 添加事务监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 执行本地事务的方法
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if (StringUtils.equals("TagA", message.getTags())) { // 如果是 TagA 的消息，就提交事务
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (StringUtils.equals("TagB", message.getTags())) { // 如果是 TagB 的消息，就回滚事务
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else if (StringUtils.equals("TagC", message.getTags())) { // 如果是 TagC 的消息，就把消息设置为 中间状态 ，使其待会儿进行回查本地事物
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             * 消息回查执行的方法
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("消息的Tag:" + messageExt.getTags());
                // 消息来回查的时候，进行提交事务  UNKNOW 消息会回查
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        // 启动producer
        producer.start();

        // 分别给每一个 Tag 绑定一条消息
        for (int i = 0; i < tags.length; i++) {
            // 创建消息对象，指定主题Topic、Tag和消息体
            Message msg = new Message("TransactionTopic", tags[i], ("Hello Transaction" + i).getBytes());
            // 发送消息
            SendResult result = producer.sendMessageInTransaction(msg, null);
            // 打印发送结果
            System.out.println("发送结果:" + result.getSendStatus());
        }

        return "success";
    }


}
