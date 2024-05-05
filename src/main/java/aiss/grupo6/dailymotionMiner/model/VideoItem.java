
package aiss.grupo6.dailymotionMiner.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoItem {

    @JsonProperty("list")
    private List<Video> items;

    @JsonProperty("list")
    public List<Video> getItems() {
        return items;
    }

    @JsonProperty("list")
    public void setItems(List<Video> items) {
        this.items = items;
    }

}
