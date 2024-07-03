package gift.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Option {
    int id;
    String option;

    public int getId() {
        return id;
    }

    public String getOption() {
        return option;
    }

    public Option(int id, String option) {
        this.id = id;
        this.option = option;
    }

}