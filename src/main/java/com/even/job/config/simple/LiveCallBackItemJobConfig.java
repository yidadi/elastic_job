package com.even.job.config.simple;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.even.job.job.simple.LiveCallBackItemJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiveCallBackItemJobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private JobEventConfiguration jobEventConfiguration;

    @Bean
    public LiveCallBackItemJob liveCallBackItemJob() {
        return new LiveCallBackItemJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler liveCallBackItemScheduler(final LiveCallBackItemJob liveCallBackItemJob, @Value("${live.callback.cron}") final String cron, @Value("${live.callback.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${live.callback.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(liveCallBackItemJob, regCenter, getLiteJobConfiguration(liveCallBackItemJob.getClass(), cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }
}
