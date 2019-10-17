package framework;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by pvtyuan on 2019/10/17.
 */
public class MyClassLoader extends URLClassLoader {
    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            InputStream inputStream = getClassInputStream(name);
            if (inputStream == null) {
                return super.loadClass(name);
            }
            byte [] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class " + name + " not found");
        }
    }

    private InputStream getClassInputStream(String name) {
        return null;
    }
}
