[![fi-ya](https://circleci.com/gh/fi-ya/EchoServerSAYL.svg?style=shield)](https://circleci.com/docs/)
# Echo Server
This project is a results of a joint collaboration effort between [Safia](https://github.com/fi-ya) and [Yuyi](https://github.com/yuyi365).
It was built following [these specifications](https://github.com/8thlight/apprenticeship_syllabus/blob/master/shared_resources/projects/http_server/01_beginner/echo_server.md).


> 
> An echo server is an application that allows a client and a server to connect so a client can send a message to the server and the server can receive the message and send, or echo, it back to the client.
> 

## How it works
1. Server creates a ServerSocket on a specific port which it listens for client connections.
2. Client makes request to ServerSocket endpoint (hostname + port number).
3. Server accepts connection and returns a new Socket connection bound to IP + port and continues to listen to further client connections.
4. Input (from client to server) and output (from server to client) streams are created to allow for transfer of messages via the socket connection
5. When client types in exit command the input/output streams close and ServerSocket and clientSocket close.

## Run project
The following steps will allow you to run the game in your terminal.
1. Clone repository `git clone git@github.com:fi-ya/EchoServerSAYL.git`
2. Check to see if you have the correct version of Java (18.0.1.1) installed `java --version`>
Choose to open build/run the server socket either with an IDE or in the terminal (CLI) using the following steps:
- IntelliJ/VS Code: open `src/main/java/echoserver.App` and pressing green `play` button next to `echoserver.App.Main()` or click `Ctrl + Shift + R`.
- Terminal: cd into project folder and run the command `./gradlew run`
7. Once build complete, you should see a message in the terminal `[+] Listening for connection on port 1234`
8. Check to see if you have [netcat](https://brewinstall.org/install-netcat-on-mac-with-brew/). To install run `brew install netcat`
9. Start a client connection by running the following command in the terminal `nc localhost 1234`
10. Server console should show connection made `[+] Listening for client server input`
11. You can now send messages in client terminal and see messaged echoed back from server.

_Note:_ To create multiple clients repeat step 9.

## Close Project
Close the server socket:
- IntelliJ/VS Code: press the red `stop` button next to `echoserver.App.Main()`
- Terminal: exit via `CTR + C`

Close the client socket:
- Terminal: submit the input `bye`

## Run tests
You can find all tests in `src/test/java` and run the test suite using either of the following steps. 
- Open a test file and either click on green play button next to the class name or run `Ctrl + Shift + R`
- Terminal: cd into project folder and run the command `./gradlew test` or for more information run `./gradlew test --info` 

## Functional Requirements
A user should be able to interact with the echo server as follows:

- [x] When a client sends a message to the server, the server sends a response back to the client containing the original message. 
- [x] A client can send multiple messages to the server and get the echoed response back each time.
- [x] Multiple clients can send messages to server and get back their proper responses.

## Implementation Requirements
- [x] The server should establish a socket connection with the client using a low-level socket library. The goal of this exercise is to work with sockets directly.
- [x] The server should accept and return streams of data rather than raw strings.
- [x] The echo server should be covered by a robust suite of tests.
