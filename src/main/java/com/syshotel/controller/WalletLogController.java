package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.service.IWalletLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 财务交易日志接口
 */
@Controller
@RequestMapping(value="/wallet")
public class WalletLogController {

    private static Logger logger = LoggerFactory.getLogger(WalletLogController.class);

    @Autowired
    private IWalletLogService iWalletLogService;


    //获取列表
    @RequestMapping(value="/getWalletList",method=RequestMethod.GET )
   // @ResponseBody
    public String getWalletList(SearchVo searchVo, PageBean page, Model model){
        logger.info("********** 进入 getWalletList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        model.addAttribute("walletList", iWalletLogService.selectWalletList(searchVo,page));
        model.addAttribute("searchVo", searchVo);
        return "admin/wallet/wallet-list";
    }

    //根据id获取
    @RequestMapping(value="/getWalletById/{walletId}",method=RequestMethod.GET )
    public String getWalletById(@PathVariable("walletId") int id,String view,Model model){
        logger.info("********** 进入 getWalletById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("walletInfo",iWalletLogService.getById(id));
        return view;
    }

    //删除
    @RequestMapping(value="/delete/{walletId}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteWallet(@PathVariable("walletId")  int walletId){
        logger.info("********** 进入 deleteWallet 方法,walletId={}********** ",new Object[]{walletId});
        return iWalletLogService.deleteById(walletId);
    }


    //进行多选删除
    @RequestMapping(value = "/deleteWalletByChoice", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteWalletByChoice(String choiceId) {
        logger.info("************  进入  deleteWalletByChoice 方法,参数choiceId={} ************ ",new Object[]{ choiceId});
        return iWalletLogService.deleteByChoiceId(choiceId);
    }
}
