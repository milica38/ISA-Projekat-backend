package com.ISA.service.definition;

import com.ISA.domain.model.*;
import com.ISA.domain.model.BoatProfile;
import com.ISA.domain.model.HomeProfile;
import com.ISA.domain.model.User;


public interface EmailService {
     void sendEmailForRegistration(User user);
     void sendEmailForHouseReservation(User user, HomeReservation reservation);
     void sendEmailForBoatReservation(User user, BoatReservation reservation);
     void sendEmailForAdventureReservation(User user, AdventureReservation reservation);
     void sendEmailForHouseAction(User user, HomeProfile homeProfile);
     void sendEmailForBoatAction(User user, BoatProfile boatProfile);

}
