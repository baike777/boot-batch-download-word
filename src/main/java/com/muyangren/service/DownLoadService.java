package com.muyangren.service;

import com.muyangren.entity.Down;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: com.muyangren.service
 * @Version: 1.0
 */
public interface DownLoadService {
    /**
     * 批量下载
     * @param response
     * @param down
     */
    void downLoadBatch(HttpServletResponse response, Down down);
}
