package com.panshul.evo.Services;

import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.Object.Search.SearchObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/events")
    Call<EventMainObject> getAllEvents();

    @GET("/events?type=Gravitas")
    Call<EventMainObject> getGravitasEvents();

    @GET("/events?type=Riviera")
    Call<EventMainObject> getRivieraEvents();

    @GET("/events?type=Hackathon")
    Call<EventMainObject> getHackathonEvents();

    @GET("/events?type=Workshop")
    Call<EventMainObject> getWorkshopEvents();

    @GET("/events?type=Speaker")
    Call<EventMainObject> getSpeakerEvents();

    @GET("/events?type=Cultural")
    Call<EventMainObject> getCulturalEvents();

    @GET("/events?type=NGO")
    Call<EventMainObject> getNGOEvents();

    @GET("/events/popular")
    Call<List<PopularMainObject>> getPopular();

    @GET("/events/get/{eventId}")
    Call<EventSpecificObject> getSpecificEvent(@Path("eventId") String eventID);

    @GET("/events/search")
    Call<List<SearchObject>> getSearch(String input);


}
