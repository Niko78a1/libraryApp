package dtu.library.acceptance_tests;

import static org.mockito.Mockito.mock;
import dtu.library.app.EmailServer;
import dtu.library.app.LibraryApp;

public class MockEmailHolder {

    private EmailServer mockEmailServer;

    public MockEmailHolder(LibraryApp library) {
        // opretter mock instans af email server
        this.mockEmailServer = mock(EmailServer.class);

        // Vi smider mocken videre til appen
        library.setEmailServer(mockEmailServer);

    }

    // getter for at få email server
    public EmailServer getMockEmailServer() {
        return mockEmailServer;
    }
    
}
