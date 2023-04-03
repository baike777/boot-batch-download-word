package com.muyangren.controller;

import com.muyangren.entity.Down;
import com.muyangren.service.DownLoadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: com.muyangren.controller
 * @Version: 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("muyangren")
public class DownLoadController {

    @Resource
    private DownLoadService downLoadService;

    /**
     * 批量下载
     */
    @PostMapping("/downloadBatch")
    public void downloadBatch(HttpServletResponse response, @RequestBody Down down) {
        downLoadService.downLoadBatch(response, down);
    }
}
