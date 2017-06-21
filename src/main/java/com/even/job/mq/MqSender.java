package com.even.job.mq;

import com.even.job.annation.Log;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by yidadi on 17-5-22.
 */
@Component
public class MqSender {
    @Log
    private Logger log;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @param routingKey
     * @param msg
     */
    public void send(String routingKey,Object msg){
        Objects.requireNonNull(routingKey);
        Objects.requireNonNull(msg);
        if(log.isDebugEnabled()){
            log.debug("send the msg start the routingKey={},msg={}",routingKey,msg);
        }
        amqpTemplate.convertAndSend(routingKey,msg);
    }

    /**
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void send(String exchange,String routingKey,Object msg){
        Objects.requireNonNull(exchange);
        Objects.requireNonNull(routingKey);
        Objects.requireNonNull(msg);
        if(log.isDebugEnabled()){
            log.debug("send the msg start the exchange={}, routingKey={},msg={}",exchange,routingKey,msg);
        }
        amqpTemplate.convertAndSend(exchange,routingKey,msg);
    }
}
