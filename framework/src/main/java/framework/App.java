package framework;

import lombok.extern.slf4j.Slf4j;
import service.Service;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@Slf4j
public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws Exception {

        Class.forName("service.Service");

        URL serviceUrl = new File("service/build/libs/service-1.0-all.jar").toURI().toURL();
        URLClassLoader serviceLoader = new URLClassLoader(new URL[] {serviceUrl}, App.class.getClassLoader());
        log.info("service loader: {}", serviceLoader);

        serviceLoader.loadClass("service.Service");


        URL appUrl = new File("app/build/libs/app-1.0-all.jar").toURI().toURL();
        URLClassLoader appLoader = new NewClassLoader(new URL[] {appUrl}, serviceLoader);
        log.info("app loader: {}", appLoader);


        Class lclass = appLoader.loadClass("app.Library");
//        log.info("lclass: {}", lclass.getClassLoader());
//
//        Constructor constructor = lclass.getConstructor();
//        Service library = (Service) constructor.newInstance();
//        Method method = lclass.getMethod("hello");
//        method.invoke(library);
//        library.hello();
//        log.info("frame: {}", App.class.getClassLoader());
//        log.info("lib: {}", library.getClass().getClassLoader());

//        System.out.println(new App().getGreeting());
    }
}
