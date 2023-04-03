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
public class DownLoadThreadTest implements Runnable {

    private List<Down> newDownList;

    private CountDownLatch countDownLatch;

    private  String filePath;

    public DownLoadThreadTest(List<Down> newDownList, CountDownLatch countDownLatch, String filePath) {
        this.newDownList = newDownList;
        this.countDownLatch = countDownLatch;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {

        }
    }
}
