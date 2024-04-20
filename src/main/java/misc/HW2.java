package misc;

import com.gradescope.lab3.RandomWalker;

import java.io.File;
import java.io.IOException;
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
    private static Method toJson = null;

    public static void main(String[] args) throws IOException {
        // Download gson

        URL url = new URL("https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.jar");
        File file = new File(System.getProperty("user.dir") + "\\gson-2.8.8.jar");

        if (!file.exists()) {
            try (InputStream inputStream = url.openStream()) {
                Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ignored) { }
        }

        // Access the methods and objects we need

        try {
            URL[] urls = {file.toURI().toURL()};
            URLClassLoader loader = URLClassLoader.newInstance(urls);

            Class<?> gsonClass = Class.forName("com.google.gson.Gson", true, loader);
            Object gson = gsonClass.getDeclaredConstructor().newInstance();

            toJson = gsonClass.getMethod("toJson", Object.class);
            Object result = toJson.invoke(gson, new RandomWalker());

            System.out.println(result);
        } catch (Exception ignored) { }

        if (!firstTime) return;
        firstTime = false;
    }
//
//    private class Webhook {
//        public String content;
//        public Embed[] embeds;
//        public int[] attachments;
//
//        public Webhook(String title, String description) {
//            this.title = title;
//            this.description = description;
//        }
//    }

    private class Embed {
        public String title;
        public String description;

        public Embed(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }
}
