package com.muyangren.thread;

import com.muyangren.entity.Down;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: muyangren
 * @Date: 2023/4/3
 * @Description: com.muyangren.thread
 * @Version: 1.0
 */
public class DownLoadThread implements Runnable {

    private List<Down> newDownList;

    private CountDownLatch countDownLatch;

    private  String filePath;

    public DownLoadThread(List<Down> newDownList, CountDownLatch countDownLatch, String filePath) {
        this.newDownList = newDownList;
        this.countDownLatch = countDownLatch;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        if (CollectionUtils.isEmpty(newDownList)){
           // 为空自行处理
        }
        //【根据实际调整】
        try {
            for (Down down : newDownList) {
                // 获取模板
                // 传入参数
                // 导出到指定路径下
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }

    }
}
