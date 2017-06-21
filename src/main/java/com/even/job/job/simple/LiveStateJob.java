package com.even.job.job.simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.even.job.util.Constant;
import com.even.job.annation.Log;
import com.even.job.mq.MqSender;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

public class LiveStateJob implements SimpleJob {
    @Log
    private Logger log;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MqSender mqSender;
    @Override
    public void execute(final ShardingContext shardingContext) {
        log.info("job start,jobName={},taskId={}", shardingContext.getJobName(), shardingContext.getTaskId());
        Set<String> liveStateData = redisTemplate.opsForSet().members(Constant.Live_State_Close_Fail);
        mqSender.send("haixue_test1","LiveStateJob");
        liveStateData.forEach(data->{
            try {
                log.info("req the end to get the data");
                //TODO http
                if(true){
                    redisTemplate.opsForSet().remove(Constant.Live_State_Close_Fail,data);
                }
            }catch (Exception e){
                log.error("Job error", e.getMessage(), e);
                redisTemplate.opsForList().leftPush(Constant.Live_State_Close_Fail, data);
            }
        });
    }
}
