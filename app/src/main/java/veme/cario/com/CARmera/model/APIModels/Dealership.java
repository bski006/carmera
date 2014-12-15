package veme.cario.com.CARmera.model.APIModels;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by bski on 11/26/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dealership {

    public List<VehicleDealership> getDealerships() {
        return dealerships;
    }

    public void setDealerships(List<VehicleDealership> dealerships) {
        this.dealerships = dealerships;
    }

    @JsonProperty
    private List<VehicleDealership> dealerships;

}
