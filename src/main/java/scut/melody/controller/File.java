package scut.melody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class File {
    @RequestMapping("/download")
    public String download(@RequestParam("url") String url, HttpServletResponse response) {
        System.out.println(url);
        String filename = url.substring(url.lastIndexOf("/")+1);
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/octet-stream");

            OutputStream os = response.getOutputStream();
            java.io.File diskFile = new java.io.File(url);
            FileInputStream fis = new FileInputStream(diskFile);
            response.setHeader("Content-Length", String.valueOf(diskFile.length()));
            byte[] buf = new byte[(int) diskFile.length()];
            fis.read(buf);
            os.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}



