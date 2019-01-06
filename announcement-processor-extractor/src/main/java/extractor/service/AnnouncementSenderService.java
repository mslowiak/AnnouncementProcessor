package extractor.service;

import extractor.entity.Announcement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

        try {

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

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            log.info("Output from server: \n");

            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (
                MalformedURLException e) {
            log.error("Exception:", e);
        } catch (IOException e) {
            log.error("Exception:", e);
        }
    }
}