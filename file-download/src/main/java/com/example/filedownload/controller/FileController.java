package com.example.filedownload.controller;

import com.example.filedownload.utils.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件下载
 * @author qzz
 * @date 2023/3/31
 */
@RestController
public class FileController {

    /**
     * 本地文件下载
     * @param request
     * @param response
     */
    @RequestMapping("/download/file")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response){
        //文件本地位置
        String filePath="F:\\1.png";
        String fileName="1.png";

        File file = new File(filePath);
        FileUtil.downloadFile(file,request,response,fileName);
    }

    /**
     * 网络文件下载
     * @param request
     * @param response
     */
    @RequestMapping("/download/online/file")
    public void downloadOnlineFile(HttpServletRequest request, HttpServletResponse response){
        //网络文件地址
        String urlPath="http://test-material-api.huariot.com/api-file/api/v1/unsigned/file/show/1/20230330/962e2875-fc40-40ae-9c68-39a83b61917e.png";
        String fileName="材料.png";
        FileUtil.downloadHttpFile(urlPath,request,response,fileName);
    }

    /**
     * 本地文件批量下载（zip压缩包批量下载）
     * @param request
     * @param response
     */
    @RequestMapping("/download/zip/file")
    public void downloadZipFile(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, String>> mapList = new ArrayList<>();
        String basePath = "C:\\Users\\Admin\\Desktop\\Desktop\\test_";
        // 模拟下载本地的3个文件，分别为测试文件1~3
        for (int i = 1; i <= 3; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("path", basePath + i + ".txt");
            map.put("name", "test_" + i + ".txt");
            mapList.add(map);
        }
        FileUtil.zipDirFileToFile(mapList, request, response);
    }

    /**
     * 本地文件批量下载（zip压缩包批量下载）
     * @param request
     * @param response
     */
    @RequestMapping("/download/zip/online/file")
    public void downloadZipOnlineFile(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("path", "http://test-material-api.huariot.com/api-file/api/v1/unsigned/file/show/1/20230330/962e2875-fc40-40ae-9c68-39a83b61917e.png");
        map.put("name", "图片1.png");
        mapList.add(map);

        map = new HashMap<>();
        map.put("path", "http://test-material-api.huariot.com/api-file/api/v1/unsigned/file/show/1/20230330/6441c655-c26a-4ada-9bdc-5a2e7bfd02a0.png");
        map.put("name", "图片2.png");
        mapList.add(map);

        map = new HashMap<>();
        map.put("path", "http://test-cloud-huariot-com.oss-cn-hangzhou.aliyuncs.com/MaterialMangerApp-iOS-Picture/1945/201904233371.jpg");
        map.put("name", "图片3.jpg");
        mapList.add(map);

        map = new HashMap<>();
        map.put("path", "http://test-cloud-huariot-com.oss-cn-hangzhou.aliyuncs.com/MaterialManager-Android-Picture/1945/1556018459562-20190423192049.mp4");
        map.put("name", "视频.mp4");
        mapList.add(map);

        FileUtil.zipUrlToFile(mapList, request, response);
    }

}
