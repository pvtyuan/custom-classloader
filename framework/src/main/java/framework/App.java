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

        URL url = new File("app/build/libs/app-1.0-all.jar").toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[] {url}, App.class.getClassLoader().getParent());
        log.info("new loader: {}", loader);

        Class lclass = loader.loadClass("app.Library");
        log.info("lclass: {}", lclass.getClassLoader());
        Class sclass = loader.loadClass("service.Service");
        log.info("sclass: {}", sclass.getClassLoader());
        Constructor constructor = lclass.getConstructor();
        Object library = constructor.newInstance();
        Method method = lclass.getMethod("hello");
        method.invoke(library);
        log.info("frame: {}", App.class.getClassLoader());
        log.info("lib: {}", library.getClass().getClassLoader());

//        System.out.println(new App().getGreeting());
    }
}
