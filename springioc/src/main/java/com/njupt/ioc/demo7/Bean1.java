package com.njupt.ioc.demo7;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("bean1")
public class Bean1 {

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    public void say(){
        System.out.println("say...");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }
}
