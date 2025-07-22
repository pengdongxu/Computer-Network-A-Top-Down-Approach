import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Locale;

public class UDPServer {

    private final static int PORT = 12345;
    private final static String SERVER_IP = "127.0.0.1";
    private final static int BUFFER_SIZE = 24;

    public static void main(String[] args) {
        try {
            // 创建DatagramSocket并绑定到指定端口
            DatagramSocket socket = new DatagramSocket(PORT);

            // 创建接收数据的缓冲区
            byte[] buffer = new byte[BUFFER_SIZE];

            // 创建DatagramPacket用于接收数据
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // 接收客户端发送的数据
            socket.receive(packet);

            // 获取客户端发送的数据内容
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);

            // 向客户端发送响应
            String response = received.toUpperCase(Locale.ROOT);
            System.out.println("Sending: " + response);
            byte[] responseBytes = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);

            // 关闭socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}