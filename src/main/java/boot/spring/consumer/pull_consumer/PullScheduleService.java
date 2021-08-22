package boot.spring.consumer.pull_consumer;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class PullScheduleService {

    public static void main(String[] args) throws MQClientException {

        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("GroupName1");

        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr("49.234.25.12:9876");

        scheduleService.setMessageModel(MessageModel.CLUSTERING);

        scheduleService.registerPullTaskCallback("TopicTest", new PullTaskCallback() {

            @Override

            public void doPullTask(MessageQueue mq, PullTaskContext context) {

                MQPullConsumer consumer = context.getPullConsumer();

                try {

// 获取从哪里拉取

                    long offset = consumer.fetchConsumeOffset(mq, false);

                    if (offset < 0)

                        offset = 0;

                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);

                    System.out.println(offset + "\t" + mq + "\t" + pullResult);

                    switch (pullResult.getPullStatus()) {

                        case FOUND:

                            break;

                        case NO_MATCHED_MSG:

                            break;

                        case NO_NEW_MSG:

                        case OFFSET_ILLEGAL:

                            break;

                        default:

                            break;

                    }

// 存储Offset，客户端每隔5s会定时刷新到Broker

                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

// 设置再过100ms后重新拉取

                    context.setPullNextDelayTimeMillis(100);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

        scheduleService.start();

    }

}

