package io.kkrathore.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.kkrathore.messenger.database.DatabaseClass;
import io.kkrathore.messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles= DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("kkr", new Profile(1L, "kkr", "kk", "Rathore"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
		
	}
	
	public Profile getProfile(String name) {
		return profiles.get(name);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() +1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String name) {
		return profiles.remove(name);
	}
}
