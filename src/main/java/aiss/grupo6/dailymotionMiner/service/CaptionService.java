package aiss.grupo6.dailymotionMiner.service;

import aiss.grupo6.dailymotionMiner.database.VMCaption;
import aiss.grupo6.dailymotionMiner.model.Caption;
import aiss.grupo6.dailymotionMiner.model.CaptionItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public List<VMCaption> indexCaptionsByVideoId(String idVideo) throws RestClientException {
        List<VMCaption> result = new ArrayList<>();

        String uri = path + "/video/" + idVideo + "/subtitles?fields=id,language_label,language";

        ResponseEntity<CaptionItem> response = restTemplate.exchange(uri, HttpMethod.GET, null, CaptionItem.class);
        for(Caption c: response.getBody().getItems()) {
            result.add(VMCaption.of(c));
        }

        return result;
    }
}
