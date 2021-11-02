package com.cobrowsing.api.repository.impl;

import com.cobrowsing.api.dto.CustomerMessageData;
import com.cobrowsing.api.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerRepositoryImpl implements CustomerRepository {

    List<CustomerMessageData> inMemoryCustomerData;

    @Override
    public List<CustomerMessageData> findAll() {
        return inMemoryCustomerData;
    }

    @Override
    public CustomerMessageData save(final CustomerMessageData messageData) {
        inMemoryCustomerData.add(messageData);
        return messageData;
    }
}
