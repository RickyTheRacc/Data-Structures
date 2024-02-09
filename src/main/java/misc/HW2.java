package misc;

import com.gradescope.lab3.RandomWalker;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SuppressWarnings("unused")
public class HW2 {
    private static boolean firstTime = false;

    public static void main(String[] args) throws MalformedURLException {
        // Try to download the gson library from Maven Central

        URL url = new URL("https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.jar");
        String jarPath = System.getProperty("user.dir") + "\\gson-2.8.8.jar";

        try (InputStream in = url.openStream()) {
            Files.copy(in, Path.of(jarPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) { e.printStackTrace(); }

        // Access the methods and objects we need


        try {
            File file = new File(jarPath);
            if (!file.exists()) return;

            URLClassLoader loader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
            Class<?> gsonClass = Class.forName("com.google.gson.Gson", true, loader);
            Object gson = gsonClass.getDeclaredConstructor().newInstance();

            Method toJsonMethod = gsonClass.getMethod("toJson", Object.class);
            Object result = toJsonMethod.invoke(gson, new RandomWalker());

            System.out.println(result);
        } catch (Exception e) { e.printStackTrace(); }

        if (!firstTime) return;

        firstTime = false;
    }

}
