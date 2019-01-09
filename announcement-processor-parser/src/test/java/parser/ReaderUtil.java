package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReaderUtil {

    public static Document getDocumentToTest(String resourceName) {
        Document spyDoc = null;
        try {
            String path = Paths.get(ReaderUtil.class.getResource(resourceName).toURI()).toString();
            String fileContent = ReaderUtil.readAllLinesFromFile(path);
            spyDoc = Jsoup.parse(fileContent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return spyDoc;
    }

    public static Document getDocumentToTestWithBaseUrl(String resourceName, String baseUrl){
        Document spyDoc = null;
        try {
            String path = Paths.get(ReaderUtil.class.getResource(resourceName).toURI()).toString();
            String fileContent = ReaderUtil.readAllLinesFromFile(path);
            spyDoc = Jsoup.parse(fileContent, baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return spyDoc;
    }

    private static String readAllLinesFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
