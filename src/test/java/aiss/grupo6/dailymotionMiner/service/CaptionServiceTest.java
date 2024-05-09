package aiss.grupo6.dailymotionMiner.service;

import aiss.grupo6.dailymotionMiner.database.VMCaption;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CaptionServiceTest {

    @Autowired
    CaptionService service;

    @Test
    @DisplayName("Find captions by video id")
    public void indexCaptionsByVideoId() {
        List<VMCaption> captions = service.indexCaptionsByVideoId("x8y98nu");
        assertNotNull(captions, "Captions list must not be null");
        if(!captions.isEmpty()) {
            for (VMCaption c : captions) {
                assertInstanceOf(String.class, c.getId(), "Captions id must be a string");
                assertInstanceOf(String.class, c.getName(), "Captions name must be a string");
                assertInstanceOf(String.class, c.getLanguage(), "Captions language must be a string");
            }
        }
    }

    @Test
    @DisplayName("Video not found exception")
    public void invalidVideoId() {
        try {
            List<VMCaption> videoInvalido = service.indexCaptionsByVideoId("123");
        } catch(HttpClientErrorException e){
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}