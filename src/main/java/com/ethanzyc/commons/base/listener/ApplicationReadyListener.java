package com.ethanzyc.commons.base.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 应用启动刷新关闭日志
 * @author ethan
 */
@SuppressWarnings("rawtypes")
@Slf4j
public class ApplicationReadyListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 初始化环境变量
            log.info("==========初始化环境变量==============");
        } else if (event instanceof ApplicationPreparedEvent) {
            // 初始化完成
            log.info("==========初始化完成==============");
        } else if (event instanceof ContextRefreshedEvent) {
            // 应用刷新
            log.info("==========应用刷新==============");
        } else if (event instanceof ApplicationReadyEvent) {
            // 应用已启动完成
            log.info("=================================");
            String server = ((ApplicationReadyEvent)event).getSpringApplication().getAllSources().iterator().next().toString();
            log.info("系统[{}]启动完成!!!", server.substring(server.lastIndexOf(".") + 1));
            log.info("=================================");
        } else if (event instanceof ContextStartedEvent) {
            // 应用启动，需要在代码动态添加监听器才可捕获
            log.info("==========应用启动==============");
        } else if (event instanceof ContextStoppedEvent) {
            // 应用停止
            log.info("==========应用停止==============");
        } else if (event instanceof ContextClosedEvent) {
            // 应用关闭
            log.info("==========应用关闭==============");
        } else {
        }
    }
}
