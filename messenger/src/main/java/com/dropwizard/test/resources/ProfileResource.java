package com.dropwizard.test.resources;


import com.codahale.metrics.annotation.Timed;
import com.dropwizard.test.database.DatabaseClass;
import com.dropwizard.test.model.Profile;
import com.dropwizard.test.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService profileService = new ProfileService();

    @GET
    @Timed
    public List<Profile> getProfiles(){
        return profileService.getAllProfiles();
    }

    @GET
    @Timed
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }

    @DELETE
    @Timed
    @Path("/{profileName}")
    public void removeProfile(@PathParam("profileName") String profileName) {
        profileService.removeMessage(profileName);
    }

    @POST
    @Timed
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @PUT
    @Timed
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }
}

