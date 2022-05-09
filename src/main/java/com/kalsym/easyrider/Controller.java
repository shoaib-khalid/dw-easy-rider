/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalsym.easyrider;

import com.google.gson.JsonArray;
import java.util.List;
import static javax.management.Query.value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Kalsym
 */
@RestController
@RequestMapping("easyRider/location")
public class Controller {

    // private RiderService riderService;
    private String location="";
    private boolean flag= false;
    @GetMapping("/{lat}/{longt}")
    public String submitOrder(@PathVariable(name = "lat") String lat, @PathVariable(name = "longt") String longt) throws Exception {
        String latlng = lat + "," + longt;
        EasyRider easyRider = new EasyRider();
        String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=AIzaSyCFhf1LxbPWNQSDmxpfQlx69agW-I-xBIw";
        WebClient webClient = WebClient.create();
        easyRider = webClient.get().uri(URL).retrieve().bodyToMono(EasyRider.class).block();
        
        List<AddressComponent> addressComponent = easyRider.results.get(0).getAddress_components();
        for(AddressComponent ac: addressComponent)
        {
            if(ac.types.contains("locality") && ac.types.contains("political"))
            {
                location = ac.long_name;
            }
           
         
        }
         System.out.println("Checking output"+ location);
            if(location.equals("Islamabad"))
            {
                return "Location is Islamabad, Order can proceed";
                
            }
            else
            {
                
                return "Location is not Islamabad";
            }
       
       

       
    }

   /* public boolean hasValue(EasyRider easyRider, String long_name, String lamabad) {
          for(int i = 0; i < easyRider.results.size() ; i++) {  // iterate through the JsonArray
        // first I get the 'i' JsonElement as a JsonObject, then I get the key as a string and I compare it with the value
        if(easyRider.results.get(i).getAsJsonObject().get(long_name).getAsString().equals(lamabad)) return true;
    }
    return false;
    }*/

}
