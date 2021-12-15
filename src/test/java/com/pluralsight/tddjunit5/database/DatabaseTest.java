package com.pluralsight.tddjunit5.database;

import com.pluralsight.tddjunit5.util.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DatabaseTest {

    @Mock
    private Database database;

    private Credentials credentials = new Credentials("Dee Dee","Fl0W3r");

    @Test
    @DisplayName("When providing the credentials for the same user, then access is allowed")
    void testSameUserSuccessfulLogin(){
        when(database.login(credentials)).thenReturn(true);
        Credentials sameCredentials = new Credentials("Dee Dee","Fl0W3r");
        assertTrue(database.login(sameCredentials));
    }

    @Test
    @DisplayName("When providing credentials for a different user, then access is not allowed")
    void testDifferentUserFailedLogin(){
        when(database.login(credentials)).thenReturn(true);
        Credentials differentCredentials = new Credentials("Dexter","%pour5((Lm§5p'q(4itF'");

        /*To improve test coverage*/
        differentCredentials.setUsername("Dexter");
        differentCredentials.setPassword("%pour5((Lm§5p'q(4itF'");

        assertNotEquals(differentCredentials.getUsername(), credentials.getUsername());
        assertNotEquals(differentCredentials.getPassword(), credentials.getPassword());
        assertNotEquals(differentCredentials.hashCode(),credentials.hashCode());
        assertFalse(database.login(differentCredentials));
    }
}
