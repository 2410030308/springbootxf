package com.yuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(HttpServletRequest servletRequest, @RequestParam("file") MultipartFile file)
            throws IOException {
        // 如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            // 上传文件路径
            String uploadPath = "F:/upload/";
            // 上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(uploadPath, filename);
            // 判断路径是否存在,没有创建
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文档中
            File file1 = new File(uploadPath + File.separator + filename);
            file.transferTo(file1);
            Map<String, Object> res = new HashMap<>(16);
            // 返回的是一个url对象
            res.put("url", file1);
            return res;
        } else {
            return null;
        }
    }
    }
