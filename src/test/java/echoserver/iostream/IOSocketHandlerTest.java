package echoserver.iostream;

import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.mockito.Mockito.*;


public class IOSocketHandlerTest {
    @Test
    public void testInputOutputStreamMessages() throws IOException {
        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenAnswer(new ReturnsElementsOf(List.of("hello", "world! ", "bye")));

        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput);

        verify(mockServerOutput).println("Server response: hello");
        verify(mockServerOutput).println("Server response: world! ");
        verify(mockServerOutput).println("Server response: bye");
    }
    @Test
    public void testInputOutputStreamClosesWhenByeMessageSent() throws IOException {
       BufferedReader mockClientInput = mock(BufferedReader.class);
       PrintWriter mockServerOutput = mock(PrintWriter.class);
       when(mockClientInput.readLine()).thenReturn("bye");

       IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput);

       verify(mockServerOutput).println("Server response: bye");
       verify(mockServerOutput, times(1)).close();
       verify(mockClientInput, times(1)).close();
    }
}
