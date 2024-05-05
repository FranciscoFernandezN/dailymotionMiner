package aiss.grupo6.dailymotionMiner.controller;

import aiss.grupo6.dailymotionMiner.database.VMCaption;
import aiss.grupo6.dailymotionMiner.database.VMChannel;
import aiss.grupo6.dailymotionMiner.database.VMComment;
import aiss.grupo6.dailymotionMiner.database.VMVideo;
import aiss.grupo6.dailymotionMiner.exception.ChannelNotFoundException;
import aiss.grupo6.dailymotionMiner.exception.InternalErrorException;
import aiss.grupo6.dailymotionMiner.repository.ChannelRepository;
import aiss.grupo6.dailymotionMiner.service.CaptionService;
import aiss.grupo6.dailymotionMiner.service.ChannelService;
import aiss.grupo6.dailymotionMiner.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dailymotionminer")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;


    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CaptionService captionService;

    @Value( "${message.channelNotFound}" )
    private String channelError;

    @Value( "${message.internalError}" )
    private String internalError;

    //GET http://localhost:8083/dailymotionminer/{id}
    @GetMapping("/{id}")
    public VMChannel findChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos) throws Exception{
        try {
            VMChannel result = this.channelService.findChannelById(id);
            List<VMVideo> videosCanal = this.videoService.indexVideosById(id, maxVideos);
            for (VMVideo v : videosCanal) {
                List<VMCaption> subtitulosVideo = this.captionService.indexCaptionsByVideoId(v.getId());

                v.setCaptions(subtitulosVideo);
            }

            result.setVideos(videosCanal);
            return result;
        }   catch(HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new ChannelNotFoundException(channelError);
            }else{
                throw new InternalErrorException(internalError);
            }

        }catch (RuntimeException e) {
            throw new InternalErrorException(internalError);
        }
    }

    //POST http://localhost:8083/dailymotionminer/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel createChannel(@PathVariable String id, @RequestParam(required = false) Integer maxVideos) throws Exception{
        VMChannel canal = findChannel(id, maxVideos);
        canal = channelRepository.save(canal);
        return canal;
    }

}
