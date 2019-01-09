package parser.walker.helpers;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

@Getter
@Setter
public class WalkerInfo {
    private int walkPageUrlNumber;
    private String walkPageUrl;
    private Document walkPageDocument;
    private int requestedAnnouncementDivNumber;
}