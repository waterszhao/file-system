package com.waters.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.waters.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    @RequestMapping("/fileList")
    public String listFile(HttpServletRequest request,String path){
        ServletContext sc = request.getSession().getServletContext();

        String uploadFilePath = sc.getRealPath("/"+path)+"/";
        File file = new File(uploadFilePath);

        List<String> fileList = new ArrayList<>();
        List<String> folderList = new ArrayList<>();
        File[] files = file.listFiles();

        if (files != null) {
            for (File file1 : files) {
                if(file1.isFile())
                    fileList.add(file1.getName());
                else
                    folderList.add(file1.getName());
            }
        }
        request.setAttribute("fileList", fileList);
        request.setAttribute("folderList", folderList);
        System.out.println(path);
        request.setAttribute("path", path+"/");
        int index = path.lastIndexOf("/", path.length() - 2);
        String lastPath = "";
        if(index != -1){
            lastPath = path.substring(0, index);
        }
        request.setAttribute("lastPath",lastPath);

        return "/file/fileList";
    }

    @RequestMapping("/fileContent")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        ServletContext servletContext = request.getSession().getServletContext();
        String realPath = servletContext.getRealPath("/" + path);
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        System.out.println(fileName);
        response.setContentType(servletContext.getMimeType(path)); //设置返回的文件类型
        response.setHeader("content-disposition", "filename=" + MimeUtility.encodeWord(fileName));
        response.setCharacterEncoding("UTF-8");

        OutputStream outStream = response.getOutputStream(); //得到向客户端输出二进制数据的对象
        outStream.write(FileUtils.getStringFromFile(realPath)); //输出数据
        outStream.flush();
        outStream.close();
    }

    @RequestMapping("/download")
    public void download(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = filePath + fileName;
        ServletContext servletContext = request.getSession().getServletContext();
        String realPath = servletContext.getRealPath("/" + path);

        response.setContentType(servletContext.getMimeType(path)); //设置返回的文件类型
        response.setHeader("content-disposition", "attachment;filename=" + MimeUtility.encodeWord(fileName));
        OutputStream outStream = response.getOutputStream(); //得到向客户端输出二进制数据的对象

        outStream.write(FileUtils.getStringFromFile(realPath)); //输出数据
        outStream.flush();
        outStream.close();
    }
}
