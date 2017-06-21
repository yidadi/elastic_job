package com.even.job.controller;

import com.even.job.annation.Log;
import com.even.job.util.Constant;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yidadi on 17-5-11.
 */
@RestController
@RequestMapping("/liveCallback")
public class LiveCallbackController {
    @Log
    private Logger log;
    private Object object;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @RequestMapping(value = "/handler",method = RequestMethod.GET)
    public void handler(String ClassNo,String Operator,String Action,Long totalusernum){
        //TODO avoid
        synchronized (object){
            if(Constant.Live_End.equals(Action)){
                if(redisTemplate.opsForSet().isMember(Constant.Live_Call_Back_Start,ClassNo)){
                    return;
                }else{
                    log.info("get the live push ,ClassNo={}",ClassNo);
                    redisTemplate.opsForSet().add(Constant.Live_Call_Back_Start,ClassNo);
                    try {
                        //todo http
                    }catch (Exception e){
                        log.error("close the live err ",e.getMessage());
                        redisTemplate.opsForSet().add(Constant.Live_State_Close_Fail,ClassNo);
                    }
                }
            }
        }
    }
}
