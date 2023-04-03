package com.muyangren.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: muyangren
 * @Date: 2023/4/2
 * @Description: com.muyangren.utils
 * @Version: 1.0
 */
public class FileUtil {
    // 文件写出大小
    private static final int  BUFFER_SIZE = 2 * 1024;

    /**
     * 压缩后直接输出到浏览器
     * @param fileList
     * @param zipFileName
     * @param response
     */
    public static void downLoadZip(String fileList, String zipFileName, HttpServletResponse response) {
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
             ZipOutputStream zous = new ZipOutputStream(bos)) {
            // 重置
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipFileName, "UTF-8"));

            File filePath = new File(fileList);
            File[] files = filePath.listFiles();
            if (files != null && files.length != 0) {
                byte[] buf = new byte[BUFFER_SIZE];
                int len;
                for (File file : files) {
                    try (FileInputStream in = new FileInputStream(file)) {
                        zous.putNextEntry(new ZipEntry(file.getName()));
                        while ((len = in.read(buf)) != -1) {
                            zous.write(buf, 0, len);
                        }
                        zous.closeEntry();
                    }
                }
                zous.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹/文件
     *
     * @param directory 要被删除的文件夹
     */
    public static void delAllFile(File directory) {
        if (!directory.isDirectory()) {
            directory.delete();
        } else {
            File[] files = directory.listFiles();
            // 空文件夹
            if (files.length == 0) {
                directory.delete();
                return;
            }
            // 删除子文件夹和子文件
            for (File file : files) {
                if (file.isDirectory()) {
                    delAllFile(file);
                } else {
                    file.delete();
                }
            }
            // 删除文件夹自己
            directory.delete();
        }
    }

    /**
     * @return 案例文件路径
     */
    public static String getCaseInfoPath(){
        String os = System.getProperty("os.name").toLowerCase();
        //windows下
        if (os.indexOf("windows")>=0) {
            return "C://caseInfo/";
        }else{
            return "/caseInfo/";
        }
    }
}
