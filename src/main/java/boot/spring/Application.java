package boot.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("boot.spring.mapper")
public class Application {
	//docker run -d --restart=always --name rocketmq-console-ng -v D:\docker\rocketmq\rocketmq-console-ng\logs:/root/logs  -e "JAVA_OPTS=-Drocketmq.namesrv.addr=mqaddr:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 6080:8080 -t --link rmqnamesrv:mqaddr styletang/rocketmq-console-ng:1.0.0

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
