package com.project.school.util;

import com.project.school.common.SummerConstant;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**session创建和失效时执行的操作(通过SummerConstant可以获得用户登录数量，为后台管理准备)
 * @author YWD
 * @date 2019.6.11
 */
public class OnlineUserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event){
        HttpSession session=event.getSession();
        String id=session.getId()+session.getCreationTime();
            synchronized(this){
                SummerConstant.UserNum++;//用户数加一
                SummerConstant.UserMap.put(id,Boolean.TRUE);//添加用户
            }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession session=event.getSession();
        String id=session.getId()+session.getCreationTime();
        synchronized(this){
            SummerConstant.UserNum--;//用户数减-
            SummerConstant.UserMap.remove(id);//从用户组中移除掉，用户组为一个map
        }
    }
}
