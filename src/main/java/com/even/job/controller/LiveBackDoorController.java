package com.even.job.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yidadi on 17-5-12.
 */
@RestController
@RequestMapping
public class LiveBackDoorController {
    @ApiOperation(value="直播初始化数据接口", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "用户名",paramType = "query",required = true)
    })
    @RequestMapping
    public void liveSyn(List<Long> data){

    }
}
