/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalsym.easyrider;

import java.util.List;

import static javax.management.Query.value;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Kalsym
 */
@RestController
@RequestMapping("easyRider/location")
public class Controller {
    //TODO: NEXT DAY
    //TODO: RAWALPINDI

    // private RiderService riderService;
    private String location = "";
    private boolean flag = false;

    @PostMapping("submitOrder/{lat}/{longt}/{city}")
    public Response submitOrder(@PathVariable(name = "lat") String lat, @PathVariable(name = "longt") String longt, @PathVariable(name = "city") String city) throws Exception {
        String latlng = lat + "," + longt;
        EasyRider easyRider = new EasyRider();
        String systemTransactionId = StringUtility.CreateRefID("DL");
        if (city.equals("null")) {
            String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=AIzaSyCFhf1LxbPWNQSDmxpfQlx69agW-I-xBIw";
            WebClient webClient = WebClient.create();
            easyRider = webClient.get().uri(URL).retrieve().bodyToMono(EasyRider.class).block();

            List<AddressComponent> addressComponent = easyRider.results.get(0).getAddress_components();
            for (AddressComponent ac : addressComponent) {
                if (ac.types.contains("locality") && ac.types.contains("political")) {
                    location = ac.long_name;
                }


            }
        } else {
            location = city;
        }
        Response response = new Response();

        System.out.println("Checking output" + location);
        if (location.equals("Islamabad") || location.equals("ISLAMABAD") || location.equals("Rawalpindi") || location.equals("RAWALPINDI")) {
            response.setLocation(location);
            response.setPrice(0.00);
            response.setStatus("ACCEPTED");
            response.setMessage("Location is" + location + ", Order submitted");
            response.setTransactionId(systemTransactionId);

            return response;

        } else {

            response.setLocation(location);
            response.setPrice(0.00);
            response.setStatus("FAILED");
            response.setMessage(location + " out of coverage area.");

            return response;
        }


    }

    @GetMapping("getPrice/{lat}/{longt}/{city}")
    public Response getPrice(@PathVariable(name = "lat") String lat, @PathVariable(name = "longt") String longt, @PathVariable(name = "city") String city) throws Exception {
        String latlng = lat + "," + longt;
        EasyRider easyRider = new EasyRider();
        if (city.equals("null")) {
            System.out.println("Testing" + location);

            String URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=AIzaSyCFhf1LxbPWNQSDmxpfQlx69agW-I-xBIw";
            WebClient webClient = WebClient.create();
            easyRider = webClient.get().uri(URL).retrieve().bodyToMono(EasyRider.class).block();

            List<AddressComponent> addressComponent = easyRider.results.get(0).getAddress_components();
            for (AddressComponent ac : addressComponent) {
                if (ac.types.contains("locality") && ac.types.contains("political")) {
                    location = ac.long_name;
                }


            }
        } else {
            location = city;
        }
        System.out.println("Checking output" + location);
        Response response = new Response();

        System.out.println("Checking output" + location);

        if (location.equals("Islamabad") || location.equals("ISLAMABAD") || location.equals("Rawalpindi") || location.equals("RAWALPINDI")) {
            response.setLocation(location);
            response.setPrice(0.00);
            response.setStatus("SUCCESS");
            response.setMessage("SUCCESS is" + location + ",  Order can proceed");

            return response;

        } else {

            response.setLocation(location);
            response.setPrice(0.00);
            response.setStatus("FAILED");
            response.setMessage(location + " out of coverage area.");

            return response;
        }


    }


}
