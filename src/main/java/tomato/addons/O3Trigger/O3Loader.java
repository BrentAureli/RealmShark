package tomato.addons.O3Trigger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class O3Loader {
    private Map<String, byte[]> mp3Files;  // Store MP3 data as byte arrays
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
                loadFilesFromJar();
            } catch (IOException | URISyntaxException e) {
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
                    byte[] fileData = readFileToByteArray(file);
                    mp3Files.put(fileNameWithoutExtension, fileData);
                    System.out.println("Added MP3 file: " + fileNameWithoutExtension);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] readFileToByteArray(File file) throws IOException {
        try (InputStream is = new FileInputStream(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    private void loadFilesFromJar() throws IOException, URISyntaxException {
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
                    if (entry.getName().startsWith(RESOURCE_PATH) && !entry.isDirectory()) {
                        // Process each file in the audio directory
                        try {
                            String fileNameWithoutExtension = entry.getName()
                                    .replaceFirst(RESOURCE_PATH, "")
                                    .replaceFirst("[.][^.]+$", "");
                            byte[] fileData = readInputStreamToByteArray(jarFile.getInputStream(entry));
                            mp3Files.put(fileNameWithoutExtension, fileData);
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
    }

    private byte[] readInputStreamToByteArray(InputStream is) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    public void play(String fileName) {
        byte[] fileData = mp3Files.get(fileName);
        if (fileData == null) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try (InputStream is = new ByteArrayInputStream(fileData)) {
            AdvancedPlayer player = new AdvancedPlayer(is);
            player.play();
            System.out.println("Playing: " + fileName);
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }
}
