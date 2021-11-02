package com.cobrowsing.api.repository;

import com.cobrowsing.api.dto.CustomerMessageData;

import java.util.List;

public interface CustomerRepository {

    List<CustomerMessageData> findAll();

    CustomerMessageData save(final CustomerMessageData messageData);
}
