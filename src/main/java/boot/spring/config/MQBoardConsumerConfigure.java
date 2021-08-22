package boot.spring.config;

import boot.spring.consumer.MQConsumBoardListener;
import boot.spring.consumer.MQConsumeSequenceListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lockie
 * @Date: 2020/4/21 10:28
 * @Description: mq消费者配置
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "board.rocketmq.consumer")
public class MQBoardConsumerConfigure {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQBoardConsumerConfigure.class);

    private String groupName;
    private String namesrvAddr;
    private String topics;
    // 消费者线程数据量
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private Integer consumeMessageBatchMaxSize;

    @Autowired
    private MQConsumeSequenceListener mqConsumBoardListener;
//    private MQConsumBoardListener mqConsumBoardListener;

    /**
     * mq 消费者配置
     * @return
     * @throws MQClientException
     */
    @Bean
    @ConditionalOnProperty(prefix = "board.rocketmq.consumer", value = "isOnOff", havingValue = "on")
    public DefaultMQPushConsumer boardConsumer() throws MQClientException {
        LOGGER.info("  正在创建 board 消费者---------------------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        // 设置监听
        consumer.registerMessageListener(mqConsumBoardListener);//顺序消费

        /**
         * 设置consumer第一次启动是从队列头部开始还是队列尾部开始
         * 如果不是第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        //广播test
        consumer.setMessageModel(MessageModel.BROADCASTING);

        try {
            // 设置该消费者订阅的主题和tag，如果订阅该主题下的所有tag，则使用*,
            String[] topicArr = topics.split(";");
            for (String tag : topicArr) {
                String[] tagArr = tag.split("~");
                consumer.subscribe(tagArr[0], tagArr[1]);
            }
            consumer.start();
            LOGGER.info(" board consumer 创建成功消费者 groupName={}, topics={}, namesrvAddr={}",groupName,topics,namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error(" board consumer 创建失败消费者!",e);
        }
        return consumer;
    }
}