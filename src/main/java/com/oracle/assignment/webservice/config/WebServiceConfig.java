package com.oracle.assignment.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Value("${credential.username}")
    private String username;

    @Value("${credential.password}")
    private String password;

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/external/services/ws/sample-service");
    }


    @Bean
    public XsdSchema sampleServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("sample-service.xsd"));
    }

    @Bean(name = "wsdl11Definition")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("sampleService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.oracle.com/external/services/sampleservice/request/v1.0");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }

    // endpoint interceptor
    @Bean
    public CustomSecurityInterceptor customSecurityInterceptor(){
        CustomSecurityInterceptor wss4jSecurityInterceptor = new CustomSecurityInterceptor(username, password);

        return wss4jSecurityInterceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(customSecurityInterceptor());
    }
}
