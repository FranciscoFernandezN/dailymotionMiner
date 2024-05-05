package aiss.grupo6.dailymotionMiner.service;

import aiss.grupo6.dailymotionMiner.Channel;
import aiss.grupo6.dailymotionMiner.database.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Value( "${api.path}" )
    private String path;

    @Autowired
    RestTemplate restTemplate;

    public VMChannel findChannelById(String idChannel) throws RestClientException {
        VMChannel result = null;
        String uri =  path + "/channel/" + idChannel + "?fields=id,name,description,created_time";

        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.GET, null, Channel.class);
        result = VMChannel.of(response.getBody());

        return result;
    }

}
