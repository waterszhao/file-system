package com.waters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
}
