package echoserver.iostream;

import echoserver.server.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


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
    private BufferedReader createClientInputReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    private PrintWriter createClientOutputWriter(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }
    public void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try (var clientInput = createClientInputReader(clientSocket);
             var clientOutput = createClientOutputWriter(clientSocket);
        ) {
            serverLogger.listeningForClientInput();
            clientInputOutputLoop(clientInput, clientOutput, clientSocket);

        } catch(IOException ie){
            System.out.println("Input & Output stream not created");
        }
        clientSocket.close();
    }
    public void clientInputOutputLoop(BufferedReader clientInput, PrintWriter clientOutput, Socket clientSocket) throws IOException {
        String clientInputLine;

        clientOutput.println("🌻🌷-+- Welcome to Safia's & Yuyi's Echo Server! -+-🌷🌻 \n+️ Type a message and press ↵ to see the message echoed back \n* To exit the programme, type 'bye' and press ↵ \n--------------------------------------------------------" );

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
