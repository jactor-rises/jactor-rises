package com.github.jactor.rises.facade.service;

import com.github.jactor.rises.commons.datatype.Username;
import com.github.jactor.rises.commons.dto.UserDto;
import com.github.jactor.rises.facade.consumer.PersistentUserConsumer;
import com.github.jactor.rises.facade.domain.user.UserDomain;

import java.util.List;
import java.util.Optional;

public class UserDomainService {
    private final PersistentUserConsumer userConsumerService;

    public UserDomainService(PersistentUserConsumer userConsumerService) {
        this.userConsumerService = userConsumerService;
    }

    public UserDomain saveOrUpdate(UserDomain userDomain) {
        return new UserDomain(userConsumerService.saveOrUpdate(userDomain.getDto()));
    }

    Optional<UserDto> fetch(Long id) {
        return userConsumerService.fetch(id);
    }

    public Optional<UserDto> find(Username username) {
        return userConsumerService.find(username);
    }

    public List<String> findAllUsernames() {
        return userConsumerService.findAllUsernames();
    }
}
