package com.ethanzyc.commons.base.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ethan
 * @date 2019/7/26 21:25
 */
@RestController
@RequestMapping("user")
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public void getUsers() throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        HttpServletResponse response = requestAttributes.getResponse();
        PrintWriter writer = response.getWriter();

        writer.write(123);
        writer.flush();

        System.out.println("continue");

//        return "success";
    }

}
