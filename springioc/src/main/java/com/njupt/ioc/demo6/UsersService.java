package com.njupt.ioc.demo6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("usersService")
public class UsersService {
    @Value("rice")
    private String food;
//    @Autowired
//    @Qualifier("userDao")
@Resource(name = "userDao")            //等价于上面两个注解的效果
    private UserDao userDao;

    public String sayHello(String name){
        return "Hello " + name;
    }

    public void eat() {
        System.out.println("eat " + this.food);
    }

    public void save(){
        System.out.println("Service中的save");
        userDao.save();
    }
}
