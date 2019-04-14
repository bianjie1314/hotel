package com.syshotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DispatcherPageController {


    @RequestMapping(value = "/dispatcher",method = RequestMethod.GET)
    public String dispatcher(String view){
        return view;
    }

}
