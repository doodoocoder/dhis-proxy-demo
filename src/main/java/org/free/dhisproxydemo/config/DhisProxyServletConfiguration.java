package org.free.dhisproxydemo.config;

import org.free.dhisproxydemo.MyProxyServlet;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DhisProxyServletConfiguration implements EnvironmentAware {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ProxyServlet proxyServlet = new MyProxyServlet("JSESSIONID=node01r5bwqycl2hfbxwtcoc2ii2da20.node0;a=123;b=456");

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(proxyServlet, propertyResolver.getProperty("servlet_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_TARGET_URI, propertyResolver.getProperty("target_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, propertyResolver.getProperty("logging_enabled", "false"));
        return servletRegistrationBean;
    }

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "proxy.dhis.");
    }
}