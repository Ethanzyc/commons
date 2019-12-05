package com.ethanzyc.commons.base.config;

import com.ethanzyc.commons.base.listener.ApplicationReadyListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ethan
 * @date 2019/8/26 09:07
 */
@Configuration
@Slf4j
public class ListenerConfig {

    @Bean
    public ApplicationReadyListener applicationStartListener(){
        log.info("==========ApplicationReadyListener实例化==============");
        return new ApplicationReadyListener();
    }
}
