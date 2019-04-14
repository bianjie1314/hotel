package com.syshotel.controller;

import com.syshotel.common.CommonResult;
import com.syshotel.common.MessageConstant;
import com.syshotel.pojo.PicturePojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(value="/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    IPictureService iPictureService;

    @RequestMapping(consumes = "multipart/form-data", value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws IOException {
        long  startTime=System.currentTimeMillis();
        String fileName = new Date().getTime()+file.getOriginalFilename();
        String path=request.getServletContext().getRealPath("/upload")+"/"+fileName;
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        logger.info("文件上传运行时间："+String.valueOf(endTime-startTime)+"ms");


        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        Object picture = request.getSession().getAttribute("pic"+userInfo.getId());
        String cache = "";
        if (picture != null){
            cache = (String)picture;
        }

        //保存在数据库中
        PicturePojo picturePojo = new PicturePojo();
        picturePojo.setCreateTime(new Date());
        picturePojo.setPathUrl("/upload/"+fileName);
        picturePojo.setType(1);
        iPictureService.addBean(picturePojo);


        cache += (","+picturePojo.getId());
        request.getSession().setAttribute("pic"+userInfo.getId(),cache);
        logger.info("cache-->"+cache);
        return CommonResult.SUCCESS(MessageConstant.FILE_UPLOAD_SUCCESS,picturePojo);
    }
}
