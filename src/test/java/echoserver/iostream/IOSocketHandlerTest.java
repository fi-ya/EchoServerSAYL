package echoserver.iostream;

import echoserver.server.StdOutServerLogger;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static org.mockito.Mockito.*;


public class IOSocketHandlerTest {
    @Test
    void inputMessagesTriggerAccurateOutputStreamMessages() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        Socket mockClientSocket = mock(Socket.class);

        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenAnswer(new ReturnsElementsOf(List.of("hello", "world! ", "bye")));

        ioSocketHandler.handleClientSocket(mockClientSocket, serverLogger);
        ioSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput, mockClientSocket);

        verify(mockServerOutput).println("Server response: hello");
        verify(mockServerOutput).println("Server response: world! ");
        verify(mockServerOutput).println("Server response: bye");
    }
}
