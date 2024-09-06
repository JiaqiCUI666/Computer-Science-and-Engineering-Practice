package org.hit.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.hit.pojo.BlackInfo;
import org.hit.pojo.Case;
import org.hit.utils.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import org.hit.service.EmailService;

import java.util.List;

@RestController
@Slf4j
@Api(value="邮件controller",tags={"邮件及案例操作接口"})
public class EmailController {

    @Resource
    private EmailService emailService;

    @GetMapping("email/get_all_black_list")
    @Operation(summary = "获取所有黑名单邮件及地址")
    public ResponseData<Object> getAllBlackList() {
        log.info("getAllBlackList");
        List<BlackInfo> blackList = emailService.getBlackList();
        return ResponseData.builder().data(blackList).message("获取成功").status(200).successs().build();
    }

    @GetMapping("email/get_all_case_list")
    @Operation(summary = "获取所有案例")
    public ResponseData<Object> getAllCaseList() {
        log.info("getAllCaseList");
        List<Case> caseList = emailService.getCaseList();
        return ResponseData.builder().data(caseList).message("获取成功").status(200).successs().build();
    }




}
