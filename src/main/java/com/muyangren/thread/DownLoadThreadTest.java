package com.muyangren.thread;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author: muyangren
 * @Date: 2023/4/3
 * @Description: com.muyangren.thread
 * @Version: 1.0
 */
public class DownLoadThreadTest implements Runnable {

    private CountDownLatch countDownLatch;

    private  String filePath;

    public DownLoadThreadTest( CountDownLatch countDownLatch, String filePath) {
        this.countDownLatch = countDownLatch;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                // 获取模板
                Resource resource = new ClassPathResource("document" + File.separator + "word" + File.separator + "测试模板.docx");
                // 传入参数
                HashMap<String, Object> data = new HashMap<>(3);
                data.put("one","家人们谁懂啊1" );
                data.put("two","家人们谁懂啊2");
                data.put("three","家人们谁懂啊3");
                // 文件名-防止重名
                String fileName = UUID.randomUUID() + "《"+i+"》家人们.docx";
                XWPFTemplate template = XWPFTemplate.compile(resource.getInputStream()).render(data);
                // 导出到指定路径下
                OutputStream os = new FileOutputStream(filePath + fileName);
                template.writeAndClose(os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
