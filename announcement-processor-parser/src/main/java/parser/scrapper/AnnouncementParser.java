package parser.scrapper;

import parser.Announcement;

public abstract class AnnouncementParser implements SinglePageParser {
    public abstract Announcement parsePage(String url);
    abstract String getPageContent(String url);
}
