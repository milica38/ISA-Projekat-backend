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
     void sendEmailForAdventureAction(User user, AdventureProfile adventureProfile);
     void sendEmailForRegistrationApproved(User user);
     void sendEmailForRegistrationDeclined(User user);
     void sendEmailForEvaluationApproved(User user);
     void sendEmailForComplaintResponse(User user, AdventureComplaints ac);
     void sendEmailForHomeComplaintResponse(User user, HomeComplaints hc);
     void sendEmailForBoatComplaintResponse(User user, BoatComplaints bc);
     void sendEmailForPenaltyOwnerOrInstructor(User user);
     void sendEmailForPenaltyClient(User user);

}
