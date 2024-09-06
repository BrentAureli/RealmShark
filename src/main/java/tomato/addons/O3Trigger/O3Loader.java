package tomato.addons.O3Trigger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class O3Loader {
    private Map<String, InputStream> mp3Files;
    private static final String RESOURCE_PATH = "audio/";

    public O3Loader() {
        mp3Files = new HashMap<>();
        loadMp3Files();
    }

    private void loadMp3Files() {
        // Try loading resources from the local filesystem first
        File folder = new File("src/main/resources/" + RESOURCE_PATH);
        if (folder.exists() && folder.isDirectory()) {
            System.out.println("Loading files from local directory.");
            loadFilesFromDirectory(folder);
        } else {
            // If not found in the local directory, try loading from the JAR
            try (InputStream resourceStream = O3Loader.class.getClassLoader().getResourceAsStream(RESOURCE_PATH)) {
                if (resourceStream == null) {
                    System.out.println("Resource folder not found in JAR: " + RESOURCE_PATH);
                    return;
                }
                System.out.println("Loading files from JAR.");
                loadFilesFromJar(resourceStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFilesFromDirectory(File folder) {
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
        if (files != null) {
            for (File file : files) {
                try {
                    String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");
                    InputStream fileStream = new FileInputStream(file);
                    mp3Files.put(fileNameWithoutExtension, fileStream);
                    System.out.println("Added MP3 file: " + fileNameWithoutExtension);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadFilesFromJar(InputStream resourceStream) throws IOException {
        try {
            // Get the URL of the running JAR
            URL jarUrl = O3Loader.class.getProtectionDomain().getCodeSource().getLocation();

            // Convert URL to File path and open the JAR file
            String jarPath = jarUrl.toURI().getPath();
            if (jarPath.endsWith(".jar")) {
                try (JarFile jarFile = new JarFile(jarPath)) {
                    // Enumerate all entries in the JAR file
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        // Filter for files in the audio directory
                        if (entry.getName().startsWith("audio/") && !entry.isDirectory()) {
                            // Process each file in the audio directory
                            try {
                                String fileNameWithoutExtension = entry.getName().replaceFirst("audio/", "").replaceFirst("[.][^.]+$", "");
                                InputStream fileStream = jarFile.getInputStream(entry);

                                // Copy the InputStream into a ByteArrayInputStream
                                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = fileStream.read(buffer)) != -1) {
                                    byteStream.write(buffer, 0, bytesRead);
                                }
                                ByteArrayInputStream copiedStream = new ByteArrayInputStream(byteStream.toByteArray());

                                // Store the ByteArrayInputStream in the map
                                mp3Files.put(fileNameWithoutExtension, copiedStream);
                                System.out.println("Added MP3 file: " + fileNameWithoutExtension);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                System.err.println("Not running from a JAR file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(String fileName) {
        InputStream is = mp3Files.get(fileName);
        if (is == null) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try {
            AdvancedPlayer player = new AdvancedPlayer(is);
            player.play();
            System.out.println("Playing: " + fileName);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        O3Loader loader = new O3Loader();

        // Example usage
        loader.play("Control");  // Replace with the actual file name (without extension)
    }
}