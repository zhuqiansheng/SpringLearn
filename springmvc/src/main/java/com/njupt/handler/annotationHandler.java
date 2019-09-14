package com.njupt.handler;

import com.njupt.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class annotationHandler {

    @RequestMapping("/annotationTest")
    public ModelAndView annotationTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "李四");
        modelAndView.setViewName("show");
        return modelAndView;
    }

    /**
     * Model传值，String进行视图解析
     */
    @RequestMapping("/modelTest")
    public String ModelTest(Model model) {
        //填充模型数据
        model.addAttribute("name", "jerry");
        //设置逻辑视图
        return "show";
    }

    /**
     * Map传值，String进行视图解析
     */
    @RequestMapping("mapTest")
    public String MapTest(Map map) {
        //填充数据
        map.put("name", "cat");
        //设置路基视图
        return "show";

    }

    @RequestMapping("/addGoods")
    public ModelAndView add(Goods goods) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("cart");
        return modelAndView;
    }
}
