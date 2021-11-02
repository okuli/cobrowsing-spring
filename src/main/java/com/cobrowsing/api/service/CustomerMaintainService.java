package com.cobrowsing.api.service;

import com.cobrowsing.api.dto.CustomerMessageData;
import com.cobrowsing.api.repository.CustomerRepository;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerMaintainService {

    SocketIOServer socketIOServer;

    CustomerRepository customerRepository;

    public void maintainCustomers(final CustomerMessageData message) {
        final Optional<CustomerMessageData> messageDataOptional = ofNullable(message);
        messageDataOptional.filter(CustomerMessageData::getIsCustomer).ifPresent(msg -> {
            final CustomerMessageData savedCustomerMessageData = customerRepository.save(message);
            socketIOServer.getRoomOperations(msg.getRoomId()).sendEvent("newCustomerAdded", savedCustomerMessageData);
            log.debug("Customer has been added with customer data [{}]", savedCustomerMessageData);
        });
        messageDataOptional.filter(fm -> !fm.getIsCustomer()).ifPresent(msg -> socketIOServer.getBroadcastOperations().sendEvent("sendCustomerList", customerRepository.findAll()));
    }
}
