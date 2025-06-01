package ResponseModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleUserResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)

    public static class Example {

        @JsonProperty("data")
        public Data data;
        @JsonProperty("support")
        public Support support;

    }
    @JsonInclude(JsonInclude.Include.NON_NULL)

    public static class Data {

        @JsonProperty("id")
        public Integer id;
        @JsonProperty("email")
        public String email;
        @JsonProperty("first_name")
        public String firstName;
        @JsonProperty("last_name")
        public String lastName;
        @JsonProperty("avatar")
        public String avatar;

    }
    @JsonInclude(JsonInclude.Include.NON_NULL)

    public static class Support {

        @JsonProperty("url")
        public String url;
        @JsonProperty("text")
        public String text;

    }
}
