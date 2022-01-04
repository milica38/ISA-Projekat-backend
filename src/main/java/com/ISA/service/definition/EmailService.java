package com.ISA.service.definition;

import com.ISA.domain.model.User;

public interface EmailService {
     void sendEmailForRegistration(User user);
     void sendEmailForHouseReservation(User user);


}
