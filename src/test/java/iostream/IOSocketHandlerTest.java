package iostream;

import server.EchoServer;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IOSocketHandlerTest {
    @Test
    public void testSocketInputGetsCreated() throws IOException {
        final int mockPort = 5678;
        ServerSocket mockServerSocket = EchoServer.openServerSocketConnection(mockPort);
        Socket mockClientSocket = mockServerSocket.accept();

        IOSocketHandler.createClientSocketInputOutputStream(mockClientSocket);

//        assertTrue(IOSocketHandler.createClientSocketInputOutputStream(mockClientSocket).toString().contains("[+] Listening for client server input"));
        assertNotNull(IOSocketHandler.createClientInputReader(mockClientSocket));

    }

}
