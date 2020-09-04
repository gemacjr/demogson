import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "userId",
         "id",
        "title",
        "completed"
})
public abstract class QouteForCsv {

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("completed")
    private boolean completed;


}
