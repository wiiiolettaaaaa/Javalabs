package com.wiiiolettaaaaa;

import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

public class PersonTest {
    @Test
    public void testEquals() {
        EqualsVerifier.forClass(Person.class)
                .withNonnullFields("firstName", "lastName", "age")
                .verify();
    }
}