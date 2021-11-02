package com.cobrowsing.api.dto;

import lombok.Data;

@Data
public class CustomerMessageData {

    String roomId;

    String customerId;

    String advisorId;

    UserMessageData user;

    Boolean isCustomer;

    Boolean isGetCustomerData;

    Boolean isAdvisorConnected;

}
