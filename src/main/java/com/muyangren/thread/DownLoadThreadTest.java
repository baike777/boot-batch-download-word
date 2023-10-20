package com.muyangren.thread;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final CountDownLatch countDownLatch;
    private final String filePath;
    private final Integer number;

    public DownLoadThreadTest(CountDownLatch countDownLatch, String filePath, Integer number) {
        this.countDownLatch = countDownLatch;
        this.filePath = filePath;
        this.number = number;
    }

    @Override
    public void run() {

        // 获取模板
        Resource resource = new ClassPathResource("document" + File.separator + "word" + File.separator + "测试模板.docx");
        for (int i = 0; i < this.number; i++) {
            // 文件名-防止重名
            String fileName = UUID.randomUUID() + "《" + i + "》家人们.docx";
            try (OutputStream os = Files.newOutputStream(Paths.get(filePath + fileName))) {
                // 传入参数
                HashMap<String, Object> data = new HashMap<>(3);
                data.put("one", "数据数据数据数据数据数据one");
                data.put("two", "数据数据数据数据数据数据数据数据two");
                data.put("three", "数据数据数据数据数据数据数据数据数据three");
                XWPFTemplate template = XWPFTemplate.compile(resource.getInputStream()).render(data);
                // 导出到指定路径下
                template.writeAndClose(os);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
    }
}
