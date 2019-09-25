package org.free.dhisproxydemo;

import org.apache.http.HttpRequest;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chj
 * @date 2019/9/25
 * @description
 */
public class MyProxyServlet extends ProxyServlet {
    private String cookeStr;
    public MyProxyServlet(String cookeStr) {
        this.cookeStr = cookeStr;
    }

    @Override
    protected void copyRequestHeader(HttpServletRequest servletRequest, HttpRequest proxyRequest,
                                     String headerName) {
        super.copyRequestHeader( servletRequest,  proxyRequest,
                 headerName);
        //这里将Cookie写死
        setCookieManual(proxyRequest,this.cookeStr);
    }
    //手动设置cookie
    private void setCookieManual(HttpRequest proxyRequest,String cookieStr){
       // proxyRequest.addHeader("Cookie","JSESSIONID=node01r5bwqycl2hfbxwtcoc2ii2da20.node0;a=123;b=456");
        proxyRequest.addHeader("Cookie",cookieStr);

    }

}
