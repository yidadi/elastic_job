package com.even.job.mq;

import com.even.job.annation.Log;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by yiwenxiang on 2017/5/22.
 */
@Component
public class MqConsumer {
    @Log
    private Logger log;
    @RabbitListener(queues = "haixue_test1")
    public void receiveMessage1(String message) {
        log.info("receive msg = {},topic=1",message);
    }

    @RabbitListener(queues = "haixue_test2")
    public void receiveMessage2(String message) {
        log.info("receive msg = {},topic=2",message);
    }

    @RabbitListener(queues = "haixue_test3")
    public void receiveMessage3(String message) {
        log.info("receive msg = {},topic=3",message);
    }
}
