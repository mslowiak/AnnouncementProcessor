package api.repository;

import api.model.GeneralAnnouncementInfo;

import java.util.List;

public interface AnnouncementRepositoryCustom {
    List<GeneralAnnouncementInfo> getListOfAnnouncements();
}
