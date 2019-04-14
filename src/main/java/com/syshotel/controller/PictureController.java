package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.MessageConstant;
import com.syshotel.common.PageBean;
import com.syshotel.pojo.PicturePojo;
import com.syshotel.service.IPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 照片信息接口
 */
@Controller
@RequestMapping(value="/picture")
public class PictureController {

    private static Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private IPictureService iPictureService;


    //获取列表
    @RequestMapping(value="/getPictureList",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getPictureList(PicturePojo picture, PageBean page){
        logger.info("********** 进入 getPictureList 方法,picture={},page={}********** ",new Object[]{picture,page});
        return new CommonResult(iPictureService.selectPictureList(picture,page));
    }


    //删除
    @RequestMapping(value="/delete",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deletePicture(int pictureId){
        logger.info("********** 进入 deletePicture 方法,pictureId={}********** ",new Object[]{pictureId});
        iPictureService.deleteById(pictureId);
        return new CommonResult(null);
    }

    //更新
    @RequestMapping(value="/update",method=RequestMethod.PUT )
    @ResponseBody
    public CommonResult updatePicture(PicturePojo picture){
        logger.info("********** 进入 updatePicture 方法,picture={}********** ",new Object[]{picture});
        if (picture.getId() <= 0){
            return new CommonResult(MessageConstant.PARAM＿CODE,MessageConstant.PARAM_ERROR);
        }
        iPictureService.updateBean(picture);
        return new CommonResult(null);
    }

    //新增
    @RequestMapping(value="/add",method=RequestMethod.POST )
    @ResponseBody
    public CommonResult addPicture(PicturePojo picture){
        logger.info("********** 进入 addPicture 方法,picture={}********** ",new Object[]{picture});
        int phoneId = iPictureService.addBean(picture);
        return new CommonResult(picture);
    }

}
