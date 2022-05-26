package echoserver.iostream;

import echoserver.server.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static echoserver.iostream.ReadTextAsString.readFileAsString;
import static echoserver.server.EchoServer.handleClientSocketStatus;


public class IOSocketHandler implements Runnable {
    private Socket clientSocket;
    private ServerLogger serverLogger;
    public int clientConnectionCounter;

    public void handleClientSocket(Socket clientSocket, ServerLogger serverLogger) {
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    @Override
    public void run() {
        clientConnectionCounter++;
        serverLogger.numberOfClientsConnected(clientConnectionCounter);
        try {
            createClientSocketInputOutputStream(clientSocket, serverLogger);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try (var clientInput = createClientInputReader(clientSocket);
             var clientOutput = createClientOutputWriter(clientSocket);
        ) {
            serverLogger.listeningForClientInput();
            clientInputOutputLoop(clientInput, clientOutput, clientSocket);

        } catch(Exception ie){
            System.out.println("Input & Output stream not created");
        }
        handleClientSocketStatus(true);
    }

    private BufferedReader createClientInputReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private PrintWriter createClientOutputWriter(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void clientInputOutputLoop(BufferedReader clientInput, PrintWriter clientOutput, Socket clientSocket) throws Exception {
        String clientInputLine;

        String data = readFileAsString("src/main/java/resources/welcome.txt");
        clientOutput.println(data);

        while((clientInputLine = clientInput.readLine()) != null) {
            System.out.println("Server will echo this back to the client: " + clientInputLine);
            clientOutput.println("Server response: " + clientInputLine);

            if (clientInputLine.equals(("bye"))) {
                clientOutput.println("🌻🌷-+- Goodbye, please visit us soon! -+-🌷🌻\n---------------------------------------------" );
                break;
            }
        }
        clientConnectionCounter--;
        serverLogger.closedClientConnection(clientSocket.getPort());
        serverLogger.numberOfClientsConnected(clientConnectionCounter);
    }
}


