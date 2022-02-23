package com.wuxin.blog.controller.front.friend;

import com.wuxin.blog.annotation.AccessLimit;
import com.wuxin.blog.annotation.OperationLogger;
import com.wuxin.blog.annotation.VisitLogger;
import com.wuxin.blog.pojo.blog.FriendMessage;
import com.wuxin.blog.service.FriendService;
import com.wuxin.blog.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: wuxin001
 * @Date: 2021/10/01/11:23
 * @Description: 友情链接
 */
@Slf4j
@RequestMapping("/friend")
@RestController
public class FriendController {


    @Resource
    private FriendService friendService;


    @AccessLimit(seconds = 60, limitCount = 10, msg = "操作频率过高！一分钟之后再试！")
    @VisitLogger(value = "访问了友情连接",name = "友情连接")
    @GetMapping("list")
    public Result findAllFriendList(){
        return Result.ok().put("page",friendService.list()).put("message",friendService.findFriendMessage(FriendMessage.FRIEND_MESSAGE_ID));
    }



}

