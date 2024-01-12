package fr.loferga.mille_bornes.java.controller;

import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.success.Success;
import fr.loferga.mille_bornes.java.core.success.SuccessRegister;

public class ControlProfileDetails {
	
	public Success[] getProfileSuccess(UserProfile profile) {
		SuccessRegister successRegister = SuccessRegister.get();
		Success[] result = new Success[successRegister.size()];
		int i = 0;
		for (Success success : successRegister) {
			result[i++] = profile.getSuccess(success.getClass());
		}
		return result;
	}
	
}
