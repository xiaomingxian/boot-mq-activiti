package boot.spring.controller.pojo;

import lombok.Data;

@Data
public class OrderStep {
int orderId;
String price;
String msg;

public  OrderStep(){}


    public OrderStep(int orderId, String price, String msg) {

        this.orderId = orderId;
        this.price = price;
        this.msg = msg;
    }
}
