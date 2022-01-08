package com.ISA.service.definition;

import com.ISA.domain.model.*;

public interface EmailService {
     void sendEmailForRegistration(User user);
     void sendEmailForHouseReservation(User user, HomeReservation reservation);
     void sendEmailForBoatReservation(User user, BoatReservation reservation);
     void sendEmailForAdventureReservation(User user, AdventureReservation reservation);
     void sendEmailForHouseAction(User user, HomeProfile homeProfile);

}
