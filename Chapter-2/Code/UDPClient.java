import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClient{

    private final static int PORT = 12345;
    private final static String SERVER_IP = "127.0.0.1";
    private final static int BUFFER_SIZE = 24;

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        socket.connect(new InetSocketAddress(SERVER_IP, PORT));

        String message = "abcdefghij";
        byte[] sendData = message.getBytes();

        socket.send(new DatagramPacket(sendData, sendData.length));
        System.out.println("Sent: " + message);

        byte[] receiveData = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        System.out.println("Received: " + new String(receiveData, 0, receivePacket.getLength()));

        socket.close();
    }

}
