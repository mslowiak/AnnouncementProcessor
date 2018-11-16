package parser;

public abstract class AnnouncementParser implements SinglePageParser {
    public abstract Announcement parsePage(String url);
}
