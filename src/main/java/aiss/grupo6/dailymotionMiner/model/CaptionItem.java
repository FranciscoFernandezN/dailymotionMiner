
package aiss.grupo6.dailymotionMiner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionItem {

    @JsonProperty("list")
    private List<Caption> items;

    @JsonProperty("list")
    public List<Caption> getItems() {
        return items;
    }

    @JsonProperty("list")
    public void setItems(List<Caption> items) {
        this.items = items;
    }

}
