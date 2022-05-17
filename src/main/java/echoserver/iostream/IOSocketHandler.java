package echoserver.iostream;

import echoserver.server.ServerLogger;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class IOSocketHandler {
    private static BufferedReader createClientInputReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    private static PrintWriter createClientOutputWriter(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }
    public static void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try {
            BufferedReader clientInput = createClientInputReader(clientSocket);
            PrintWriter serverOutput = createClientOutputWriter(clientSocket);
            serverLogger.listeningForClientInput();
            clientInputOutputLoop(clientInput, serverOutput, clientSocket);
        } catch(IOException ie){
            System.out.println("Input & Output stream not created");
        }
    }
    public static void clientInputOutputLoop(BufferedReader clientInput, PrintWriter serverOutput, Socket clientSocket) throws IOException {
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
        clientSocket.close();
    }
}
