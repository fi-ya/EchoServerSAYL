package server;

import java.io.*;
import java.net.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//class EchoServerTest {
//
//    @Test
//    public void testEchoServerExists() throws IOException {
//        EchoServer echoServer = mock(EchoServer.class);
////        Mockito.verify(EchoServer.openConnection(), Mockito.times(1));
//        assertNotNull(echoServer.openConnection());
//    }
//
//    @Test public void openConnectionHasMessage() {
//        EchoServer mockEchoServer = mock(EchoServer.class);
//        assertNotNull("[+] Listening for connection on port 1234", mockEchoServer.openConnection());
//    }
//}