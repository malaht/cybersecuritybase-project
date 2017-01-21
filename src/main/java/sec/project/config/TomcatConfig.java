/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 */
@Configuration
public class TomcatConfig
{
    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory()
    {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory()
        {
            // TODO
        };

        factory.addContextCustomizers(new TomcatContextCustomizer()
        {
         

            @Override
            public void customize(org.apache.catalina.Context context) {
                context.setManager(new MySessionManager());
            }
        });

        return factory;
    }

    
}
