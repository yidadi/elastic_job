package com.even.job.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yidadi on 17-5-22.
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue haixue_test1() {
        return new Queue("haixue_test1");  }

    @Bean
    public Queue haixue_test2() {
        return new Queue("haixue_test2");  }

    @Bean
    public Queue haixue_test3() {
        return new Queue("haixue_test3");  }
}
