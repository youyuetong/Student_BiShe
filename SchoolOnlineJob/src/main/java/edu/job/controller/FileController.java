package edu.job.controller;

import com.alibaba.fastjson.JSONObject;
import edu.job.utils.IDGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    // 多文件上传，返回一个对象集合（附件地址，名称）
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject saveFiles(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Collection<Part> parts = null;
        try {
            parts = request.getParts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        Iterator<Part> iterator = parts.iterator();
//  名称集合，返回给前台
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Part part = iterator.next();
            // 生成实际存储的真实文件名(唯一)
//   不知道为什么，文件上传必须得包含获取到的文件名，否则报文件名、目录名或卷标语法不正确，图片上传没有这种限制
//   文件名，保存导数据库，用来界面显示
            String x = part.getSubmittedFileName();
            String u = part.getName();
            String[] arr = x.split("\\.");
            String name = arr[arr.length-1];
            String realName = IDGenerator.generateId() + "." + name;

            list.add(realName);
            String realPath = "D:" + File.separator + "wxxcx" + File.separator + "upload";
            File file = new File(realPath);
            //如果目录不存在
            if (!file.isDirectory()) {
                //创建文件上传目录
                file.mkdirs();
            }
            // 文件存放的真实路径
            String filePath = realPath + File.separator + realName;
            try {
                part.write(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        jsonObject.put("data", list);
        return jsonObject;
    }
}
