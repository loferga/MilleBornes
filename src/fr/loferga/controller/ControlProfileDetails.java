package fr.loferga.controller;

import fr.loferga.core.account.UserProfile;
import fr.loferga.core.success.Success;
import fr.loferga.core.success.SuccessRegister;

public class ControlProfileDetails {
	
	public Success[] getProfileSuccess(UserProfile profile) {
		SuccessRegister successRegister = SuccessRegister.get();
		Success[] result = new Success[successRegister.size()];
		int i = 0;
		for (Success success : successRegister) {
			result[i++] = profile.getSuccess(success);
		}
		return result;
	}
	
}
