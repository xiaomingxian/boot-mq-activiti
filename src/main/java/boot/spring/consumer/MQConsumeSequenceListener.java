package boot.spring.consumer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MQConsumeSequenceListener implements MessageListenerOrderly {
    public static final Logger LOGGER = LoggerFactory.getLogger(MQConsumeSequenceListener.class);

    /**
     * 默认msg里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
     * 不要抛异常，如果没有return SUCCESS ，consumer会重新消费该消息，直到return SUCCESS
     * @param msgList
     * @param consumeOrderlyContext
     * @return
     */
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgList, ConsumeOrderlyContext consumeOrderlyContext) {
        if (CollectionUtils.isEmpty(msgList)) {
            LOGGER.info("MQ接收消息为空，直接返回成功");
            return ConsumeOrderlyStatus.SUCCESS;
        }

        try {
            LOGGER.info("MQ接收到的消息msgSize=[{}]", msgList.size());
            for (MessageExt messageExt : msgList) {
                if (null == messageExt) {
                    LOGGER.info("mq消息msg为空,直接处理下一条消息");
                    continue;
                }
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String body = new String(messageExt.getBody(), "utf-8");
                //JSONObject jsonObject = JSONObject.parseObject(body);
                //LOGGER.info("MQ消息traceId={},topic={}, tags={}, 消息内容={}",jsonObject.getString("traceId"), topic,tags,body);
                LOGGER.info("MQ消息,topic={}, tags={}, 消息内容={},msg:{}", topic,tags,body,messageExt);

            }
        } catch (Exception e) {
            LOGGER.error("获取MQ消息内容异常{}",e);
        }

        // TODO 处理业务逻辑
        return ConsumeOrderlyStatus.SUCCESS;
    }

}