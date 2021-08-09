package boot.spring.controller.rocketmq;

import boot.spring.controller.pojo.OrderStep;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("seq")
public class SequenceController {


    @Autowired(required = false)
    DefaultMQProducer defaultMQProducer;


    /**
     * 发送顺序消息  模拟  生成订单 -> 付款 -> 完成 有序
     *
     * @param
     * @return
     */
    @RequestMapping("/sendSequence")
    public String sendSequence() throws Exception {
        /**
         * 造数据
         */
        List<List<OrderStep>> orders = getOrders();
        orders.stream().forEach(orderSteps -> {
                    orderSteps.forEach(order -> {


                        String jsonString = JSONObject.toJSONString(order);
                        log.info("sendSequence-发送MQ消息内容：" + jsonString);
                        Message message = new Message("TestTopic", "TestTag", UUID.randomUUID().toString(), jsonString.getBytes());
                        // 发送消息
                        try {
                            /**
                             * 参数1：消息对象
                             * 参数2：队列选择器 (用业务id取模 计算出queue index)
                             * 参数3：选择队列的业务标识-订单id
                             */
                            defaultMQProducer.send(message, new MessageQueueSelector() {
                                @Override
                                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                                    Integer id = (Integer) arg;
                                    //根据订单id选择发送哪个queue
                                    int index = id % mqs.size();
                                    log.info("sendSequence-订单id={},队列={}", id, index);
                                    return mqs.get((int) index);
                                }
                            }, order.getOrderId());
                        } catch (Exception e) {
                            log.info("sendSequence-发送消息异常：{}", e);
                        }


                    });

                }
        );
        return "success";
    }

    private List<List<OrderStep>> getOrders() {
        List<List<OrderStep>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<OrderStep> orderSteps = new ArrayList<>();
            OrderStep orderStep = new OrderStep(i, i * 10 + "", "订单生成" + i);
            OrderStep orderStepPay = new OrderStep(i, i * 10 + "", "订单支付" + i);
            OrderStep orderStepFin = new OrderStep(i, i * 10 + "", "订单完成" + i);
            orderSteps.add(orderStep);
            orderSteps.add(orderStepPay);
            orderSteps.add(orderStepFin);

            list.add(orderSteps);
        }
        return list;
    }
}
