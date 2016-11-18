package net.centro.rtb.monitoringcenter;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.net.URLClassLoader;

public class SeparateClassloaderTestRunner extends BlockJUnit4ClassRunner {

    public SeparateClassloaderTestRunner(Class<?> clazz) throws InitializationError {
        super(getFromTestClassloader(clazz));
    }

    private static Class<?> getFromTestClassloader(Class<?> clazz) throws InitializationError {
        try {
            ClassLoader testClassLoader = new TestClassLoader();
            return Class.forName(clazz.getName(), true, testClassLoader);
        } catch (ClassNotFoundException e) {
            throw new InitializationError(e);
        }
    }

    public static class TestClassLoader extends URLClassLoader {
        public TestClassLoader() {
            super(((URLClassLoader) getSystemClassLoader()).getURLs());
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            if (name.startsWith("net.centro.rtb.monitoringcenter")) {
                return super.findClass(name);
            }
            return super.loadClass(name);
        }
    }
}
