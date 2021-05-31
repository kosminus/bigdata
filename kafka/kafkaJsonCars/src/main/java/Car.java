import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Car implements Serializable {
    @JsonProperty
    String Name;
    @JsonProperty
    double Miles_per_Gallon;
    @JsonProperty
    int Cylinders;
    @JsonProperty
    double Displacement;
    @JsonProperty
    double Horsepower;
    @JsonProperty
    double Weight_in_lbs;
    @JsonProperty
    int Acceleration;
    @JsonProperty
    Date Year;
    @JsonProperty
    String Origin;
}
