package com.goeuro.server;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;

import java.io.IOException;

/**
 * Standalone Server for deploying code.
 * Created by Sam on 4/4/17.
 */
public class GrizzlyServer  {

    static final Logger LOG = Logger.getLogger(GrizzlyServer.class);

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("goeuro", "localhost", 8088);
        server.addListener(listener);
        WebappContext webappContext = new WebappContext("goeuroctx","/");
        final ServletRegistration reg = webappContext.addServlet("spring", new SpringServlet());
        reg.addMapping("/*");
        String fileLocation=args[0];
        LOG.info("[GrizzlyServer] the feed file location " + fileLocation);
        System.setProperty("feedfile", fileLocation);
        webappContext.addContextInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
        webappContext.addContextInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        webappContext.addListener("org.springframework.web.context.ContextLoaderListener");
        webappContext.addListener("org.springframework.web.context.request.RequestContextListener");
        webappContext.deploy(server);
        server.start();
        while(true) {
            System.in.read();
        }

    }

}
