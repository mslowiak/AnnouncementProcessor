package extractor.service;

import extractor.entity.Announcement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class AnnouncementSenderService {

    @Value("${net.api.url}")
    private String apiUrl;
    private MapperService mapper;

    public AnnouncementSenderService(MapperService mapper) {
        this.mapper = mapper;
    }

    public void send(Announcement announcement) {

        try { // todo refactor and consider using something else as http client

            log.debug("Mapping announcement to JSON...");
            String announcementJson = mapper.getJsonFromAnnouncement(announcement);
            log.info("Mapped announcement JSON: {}", announcementJson);

            log.info("Sending data to url: {}", apiUrl);
            URL url = new URL(this.apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(announcementJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder outputBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                outputBuilder.append(output);
            }
            log.info("Output from server: {}", outputBuilder.toString());

            conn.disconnect();

        } catch (IOException e) { // todo printing this not informative
            log.error("Exception:{}", e.getMessage());
        }
    }
}