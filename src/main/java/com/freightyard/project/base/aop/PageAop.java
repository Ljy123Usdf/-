package com.freightyard.project.base.aop;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class PageAop {

    /**
     * 实现类中的方法后缀是WithPage，通过AOP自动使用pageHelper的分页功能
     * 实现类方法中正常查询即可，返回值要是PageInfo
     * @param proceedingJoinPoint
     * @return PageInfo<T>
     * @throws Throwable
     */
    @Around("execution(* com.freightyard.project.business.*ServiceImpl.*WithPage(..))")
    public PageInfo selectEntityForPage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Integer pageNum = request.getParameter("pageNum") == null ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
            PageHelper.startPage(pageNum, pageSize);
            PageInfo page = (PageInfo) proceedingJoinPoint.proceed();
            /** 当分页方法结束后，清空ThreadLocal，避免发生莫名其妙的分页 */
            PageHelper.clearPage();
            return page;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

}
