package org.sandbox.springplayground.ui.controller;

import org.sandbox.springplayground.service.HelloService;
import org.sandbox.springplayground.ui.bean.HelloBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/hello")
public class HelloController
{
    @Resource
    private HelloService helloService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHello(ModelMap model)
    {
        HelloBean hello = helloService.sayHello();

        model.addAttribute("helloBean", hello);

        return "hello";
    }
}