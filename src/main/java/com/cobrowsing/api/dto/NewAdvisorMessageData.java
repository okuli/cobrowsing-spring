package com.cobrowsing.api.dto;

import lombok.Data;

@Data
public class NewAdvisorMessageData {

    String html;

    String roomId;

    String customerId;

    String advisorId;

    Boolean isCustomer;

}
