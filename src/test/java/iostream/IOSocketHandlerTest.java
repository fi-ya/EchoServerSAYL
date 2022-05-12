package iostream;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

public class IOSocketHandlerTest {
    @Test
    public void inputOutputStream() throws IOException {
       BufferedReader mockClientInput = mock(BufferedReader.class);
       PrintWriter mockServerOutput = mock(PrintWriter.class);
       when(mockClientInput.readLine()).thenAnswer(new ReturnsElementsOf(List.of("hello", "bye")));

       IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput);

       verify(mockServerOutput).println("Server response: hello");
    }

}
