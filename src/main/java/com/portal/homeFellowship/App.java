package com.portal.homeFellowship;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.portal.homeFellowship.configuration.JdbcConfiguration;
import com.portal.homeFellowship.configuration.MvcConfiguration;
import com.portal.homeFellowship.utility.SSLClientFactory;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import({JdbcConfiguration.class, MvcConfiguration.class})
@EnableScheduling
public class App extends SpringBootServletInitializer
{
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
    
//    @Bean
//    public TomcatServletWebServerFactory containerFactory() {
//        return new TomcatServletWebServerFactory() {
//            protected void customizeConnector(Connector connector) {
//                int maxSize = 50000000;
//                super.customizeConnector(connector);
//                connector.setMaxPostSize(maxSize);
//                connector.setMaxSavePostSize(maxSize);
//                if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
//
//                    ((AbstractHttp11Protocol <?>) connector.getProtocolHandler()).setMaxSwallowSize(maxSize);
//                    logger.info("Set MaxSwallowSize "+ maxSize);
//                }
//            }
//        };
//
//    }
    
    @Bean
    public TomcatServletWebServerFactory containerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return factory;
    }
    
    
    @Bean
	public RestTemplate restTemplate(){ 
	 RestTemplate restTemplate = new RestTemplate(SSLClientFactory.getClientHttpRequestFactory(SSLClientFactory.HttpClientType.HttpClient));  
     ((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(100000); 
     ((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(100000);
	 return restTemplate;
	}
    
    
    @Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
