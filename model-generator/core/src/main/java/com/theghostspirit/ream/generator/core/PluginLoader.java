package com.theghostspirit.ream.generator.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    private final String path;

    public PluginLoader(String path) {
        this.path = path;
    }

    private ClassLoader getClassLoader() {
        try {
            final var url = new File(this.path).toURI().toURL();
            return new URLClassLoader(new URL[]{url});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private Enumeration<JarEntry> findAllClasses() {
        try {
            return new JarFile(this.path).entries();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String pathToBinaryName(String className) {
        return className.substring(0, className.length() - 6).replace('/', '.');
    }

    private List<Class<?>> loadAllClasses() {
        try {
            var list = new ArrayList<Class<?>>();
            var cl = this.getClassLoader();

            for (var classes = this.findAllClasses(); classes.hasMoreElements(); ) {
                var c = classes.nextElement();

                if (!c.getName().endsWith(".class")) {
                    continue;
                }

                list.add(cl.loadClass(this.pathToBinaryName(c.getName())));
            }

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadPlugin(Api api) {
        this.loadAllClasses().stream()
            .filter(c -> Arrays.asList(c.getInterfaces()).contains(IPlugin.class))
            .forEach(c -> {
                try {
                    var method = c.getDeclaredMethod("transform", Api.class);
                    method.setAccessible(true);
                    method.invoke(c.getConstructor().newInstance(), api);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
    }

}
