package iostream;

import message.Connection;
import message.Listening;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOSocketHandler {

    public static BufferedReader createClientInputReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    public static PrintWriter createClientOutputWriter(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }
    
    public static void createClientSocketInputOutputStream(Socket clientSocket) throws IOException{

        try {
            BufferedReader clientInput = createClientInputReader(clientSocket);
            PrintWriter serverOutput = createClientOutputWriter(clientSocket);
            System.out.println(Listening.listeningForClientInput());
            clientInputOutputLoop(clientInput, serverOutput);

        } catch(IOException ie){
            System.out.println("Input & Output stream not created");
        }
    }

    public static void clientInputOutputLoop(BufferedReader clientInput, PrintWriter serverOutput) throws IOException {
        String clientInputLine;

        while((clientInputLine = clientInput.readLine()) != null) {
            System.out.println("Server will echo this back to the client: " + clientInputLine);
            serverOutput.println("Server response: " + clientInputLine);

            if (clientInputLine.equals(("bye"))) {
                break;
            }
        }
        serverOutput.close();
        clientInput.close();
    }
}
