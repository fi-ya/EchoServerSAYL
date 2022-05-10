package server;

import java.io.*;
import java.net.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServerTest {

    @Test
    public void testEchoServerExists() throws IOException {
        EchoServer.start();
        Mockito.verify(EchoServer.openConnection(), Mockito.times(1));
    }
}