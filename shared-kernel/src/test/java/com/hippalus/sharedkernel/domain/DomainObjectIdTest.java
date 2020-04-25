package com.hippalus.sharedkernel.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DomainObjectIdTest {

    @Test
    void shouldCreateRandomUUID(){
        final var randomId = DomainObjectId.randomId(TestId.class);
        assertNotNull(randomId);
    }

}