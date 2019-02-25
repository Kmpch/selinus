package us.kpm.rpc.server;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import us.kpm.rpc.register.SelinusRpcRegistry;

import java.util.Map;

/**
 * Description: SelinusRpcServer
 * PackageName: us.kpm.rpc.server
 *
 * @author: chenglulu
 * @version: 1.0
 * Filename:    SelinusRpcServer.java
 * Create at:  2019/2/25
 * Copyright:   Copyright (c)2019
 * Company:     songxiaocai
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2019/2/25     chenglulu    1.0         1.0 Version
 */
public class SelinusRpcServer implements ApplicationContextAware,InitializingBean {

    /**
     * 保存所有提供服务的方法： key:类的全路径,value所有的实现类
     */
    private final Map<String,Object> serviceBeanMap = Maps.newHashMap();

    /**
     * rpc服务注册类
     */
    private SelinusRpcRegistry registry;

    /**
     * 服务地址信息
     */
    private String serverAddress;

    /**
     * 在spring容器启动之后获取注册相关的bean
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //扫描所有到所有包含服务注册的注解 SelinusRpcService
        Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(SelinusRpcService.class);
        if(MapUtils.isNotEmpty(beansMap)){
            //保存bean信息   key beanName  value bean
            beansMap.values().stream()
                    .map(bean -> beansMap.put(bean.getClass()
                            .getAnnotation(SelinusRpcService.class)
                            .value().getName(),bean));
        }
    }

    /**
     * 初始化完成以后
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        //创建服务端的通信对象


    }
}
