package com.feignforsongify;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@EnableFeignClients
@Log4j2
public class FeignForSongifyApplication {

    private final SongifyProxy songifyProxy;

    public FeignForSongifyApplication(SongifyProxy songifyProxy) {
        this.songifyProxy = songifyProxy;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignForSongifyApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequest() {
        SongList songExamples = songifyProxy.showAllSongs();
        log.info(songExamples);
    }
}
