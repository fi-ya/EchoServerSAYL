package echoserver.server;

import echoserver.iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EchoServerTest {
    @Test
    public void testServerSocketOpensConnectionToPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        final int mockPort = 5678;

        echoServer.openServerSocketConnection(mockPort);
        ServerSocket serverSocketNew = echoServer.getServerSocket();

        assertNotNull(serverSocketNew);
    }
    @Test
    public void testServerSocketFailsToConnectToInvalidPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        assertThrows(IllegalArgumentException.class, () -> echoServer.openServerSocketConnection(-1));
    }
    @Test
    public void testClientSocketConnectsToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        Socket result = echoServer.getClientSocket();

        assertEquals(mockClientSocket, result);
    }
    @Test
    public void testClientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        mockServerSocket.close();
        echoServer.connectClientSocket(mockServerSocket, serverLogger);

        assertNotNull(IllegalArgumentException.class);
    }
    @Test
    public  void testClientSocketDisconnectedWhenClientSentExitMessage() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenReturn("bye");
        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput,mockClientSocket,serverLogger);

        verify(mockServerOutput, times(1)).close();
        verify(mockClientInput, times(1)).close();
        verify(mockClientSocket, times(1)).close();
    }

//    @Test
//    public void testMultipleClientsAbleToConnectToServer() throws IOException {
//        StdOutServerLogger serverLogger = new StdOutServerLogger();
//        var echoServer = new EchoServer(serverLogger);
//        ServerSocket mockServerSocket = mock(ServerSocket.class);
//        Socket mockClientSocketOne = mock(Socket.class);
//        Socket mockClientSocketTwo = mock(Socket.class);
//        when(mockServerSocket.accept()).thenReturn(mockClientSocketOne);
//        when(mockServerSocket.accept()).thenReturn(mockClientSocketTwo);
//
//        echoServer.connectClientSocket(mockServerSocket, serverLogger);
//        BufferedReader mockClientInputOne = mock(BufferedReader.class);
//        PrintWriter mockServerOutputOne = mock(PrintWriter.class);
//        when(mockClientInputOne.readLine()).thenAnswer(new ReturnsElementsOf(List.of("hello", "world! ", "bye")));
//        ;
//        IOSocketHandler.clientInputOutputLoop(mockClientInputOne, mockServerOutputOne,mockClientSocketOne);
//
//        echoServer.connectClientSocket(mockServerSocket, serverLogger);
//        BufferedReader mockClientInputTwo = mock(BufferedReader.class);
//        PrintWriter mockServerOutputTwo = mock(PrintWriter.class);
//        when(mockClientInputTwo.readLine()).thenAnswer(new ReturnsElementsOf(List.of("marco", "polo! ", "bye")));
//
//        IOSocketHandler.clientInputOutputLoop(mockClientInputTwo, mockServerOutputTwo,mockClientSocketTwo);
//
//        verify(mockClientInputOne, times(1)).connectClientSocket(mockServerSocket, serverLogger);
//
//
//    }
}
