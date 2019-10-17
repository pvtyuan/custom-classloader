package framework;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.net.URLClassLoader;

@Slf4j
public class NewClassLoader extends URLClassLoader {

    private final ClassLoader parent;

    public NewClassLoader(URL[] url, ClassLoader parent) {
        super(url, parent);
        this.parent = parent;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        log.info("new loading class: {}", name);
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {

                c = findClass(name);
                if (c == null) {
                    parent.loadClass(name);
                }
            }
            return c;
        }
    }
}
