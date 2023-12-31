package com.muyangren.service;

import com.muyangren.config.ExecutorConfig;
import com.muyangren.entity.Down;
import com.muyangren.thread.DownLoadThreadTest;
import com.muyangren.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: 仅仅作为一个测试用例-具体实现需要根据业务来实现
 * @Version: 1.0
 */
@Service
public class DownLoadServiceImpl implements DownLoadService{

    //@Resource
    //private DownMapper downMapper;

    @Value("${batch-download.page-num}")
    private Integer pageNum;

    @Value("${batch-download.number}")
    private Integer number;
    @Resource
    private ExecutorConfig executorConfig;

    @Override
    public void downLoadBatch(HttpServletResponse response) {
        // --测试用例(看效果)
        downLoadBatchTest(response);
        // --实际项目(写了个大概模板、根据业务去整合即可)
        //downLoadBatchTestActual(response,down);
    }

    private void downLoadBatchTest(HttpServletResponse response) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 测试数据 10条线程、每条线程处理100份数据
        // 文件本地统一存放路径(防止路径出现重复的情况、建议用雪花id、这里没集成就随便弄个时间戳代替
        String filePath = FileUtil.getCaseInfoPath() + System.currentTimeMillis() + File.separator;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            // 初始化多线程
            Executor executor = executorConfig.asyncServiceExecutor();

            CountDownLatch countDownLatch = new CountDownLatch(pageNum);
            for (int i = 1; i <= pageNum; i++) {
                executor.execute(new DownLoadThreadTest(countDownLatch, filePath,number));
            }
            countDownLatch.await();
            stopWatch.stop();
            System.out.println("下载共耗时：" + stopWatch.getTotalTimeSeconds()+"秒");
            // 压缩文件名称
            String zipName = "导出记录" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
            // 通过浏览器下载
            FileUtil.downLoadZip(filePath, zipName, response);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 删除文件
            FileUtil.delAllFile(file);
        }
    }

    /**
     *    大概模板、细节需要自己打磨
     *    由于需要用到mybatis-plus的分页方法、需要自行集成
     *    或者是自己举一反三写个分发方法也行
     *
     */
    private void downLoadBatchTestActual(HttpServletResponse response, Down down) {
        //// 1、先获取总数 【根据实际调整】
        //List<Down> downList = downMapper.downPage(null, down);
        //if (CollectionUtils.isEmpty(downList)) {
        //    System.out.println("无案例导出！");
        //}
        //// 2、文件本地统一存放路径
        //String filePath = FileUtil.getCaseInfoPath() + "document" + File.separator + UUID.randomUUID() + File.separator;
        //File file = new File(filePath);
        //if (!file.exists()) {
        //    file.mkdirs();
        //}
        //
        //try {
        //    // 3、初始化多线程
        //    Executor executor = executorConfig.asyncServiceExecutor();
        //    // 4、设置每25条数据创建一个线程
        //    IPage<Down> page = new Page<>(1, 25, false);
        //    long pageNum = downList.size() / page.getSize();
        //    if (downList.size() % page.getSize() != 0L) {
        //        ++pageNum;
        //    }
        //
        //    // 5、创建线程数
        //    CountDownLatch countDownLatch = new CountDownLatch((int) pageNum);
        //    for (int i = 1; i <= pageNum; i++) {
        //        page.setCurrent(i);
        //        //【根据实际调整】
        //        List<Down> newDownList = downMapper.downPage(page, down);
        //        executor.execute(new DownLoadThread(newDownList, countDownLatch, filePath));
        //    }
        //
        //    countDownLatch.await();
        //    // 压缩文件名称
        //    String zipName = "导出记录" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
        //    // 通过浏览器下载
        //    FileUtil.downLoadZip(filePath, zipName, response);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //} finally {
        //    // 删除文件
        //    FileUtil.delAllFile(file);
        //}
    }

}
