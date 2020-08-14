package com.ginger.wlfl.aop;

import com.ginger.wlfl.pojo.SysLog;
import com.ginger.wlfl.service.ISysLogService;
import com.ginger.wlfl.web.SysLogController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect //表示该类是一个通知类
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; // 访问时间
    private Class executionClass; // 访问的类
    private Method executionMethod; // 访问的方法

    // 前置通知
    @Before("execution(* com.ginger.wlfl.web.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        //访问时间
        visitTime = new Date();

        // 获取访问类
        executionClass = joinPoint.getTarget().getClass();

        // 获取访问的方法
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 判断是否有参数
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);//获取到的是无参方法对象
        } else {
            Class[] clazzArr = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                clazzArr[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName, clazzArr);//获取到的是带参方法对象
        }
    }

    // 后置通知
    @After("execution(* com.ginger.wlfl.web.*.*(..))")
    public void doAfter() {
        if (executionClass != null && executionMethod != null && executionClass != SysLogController.class) {
            //获取执行时长
            long time = new Date().getTime() - visitTime.getTime();

            //获取操作者用户名
            //方式1
            SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
            User user = (User) context.getAuthentication().getPrincipal();//获取springsecurity框架中User用户对象。
            String username = user.getUsername();
            //方式2
            //security框架在session储存user键名是SPRING_SECURITY_CONTEXT
            //User user = (User)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            //String username = user.getUsername();

            //获取访问ip
            String ip = request.getRemoteAddr();

            //获取访问资源url

            //类和方法上RequestMapping注解中的值做拼接
            String url = "";
            //获取类上注解中的值
            RequestMapping requestMapping1 = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            String classAnnoValue = requestMapping1.value()[0];
            //获取方法上注解中的值
            RequestMapping requestMapping2 = executionMethod.getAnnotation(RequestMapping.class);
            String methodAnnoValue = requestMapping2.value()[0];
            url = classAnnoValue + methodAnnoValue;

            //获取访问方法
            String method = "[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName();

            //创建SysLog对象
            SysLog sysLog = new SysLog();
            sysLog.setVisitTime(visitTime);
            sysLog.setUsername(username);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setExecutionTime(time);
            sysLog.setMethod(method);
            sysLogService.saveSysLog(sysLog);
        }
    }
}
