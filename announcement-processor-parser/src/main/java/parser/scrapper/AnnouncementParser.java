package parser.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.Announcement;

import java.io.IOException;

public abstract class AnnouncementParser {
    public abstract Announcement parsePage(String url);

    Document getPageContent(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
