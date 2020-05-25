import java.io.IOException;
import java.net.*;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        int port;
        int length = 1024;

        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.out.println("Using default Port 3000");
            port = 3000;
        }
        DatagramSocket ds = new DatagramSocket(port);
        while (true) {
            DatagramPacket dp = new DatagramPacket(new byte[length], length);
            ds.receive(dp);
            byte[] msg = dp.getData();
            String strMsg = new String(msg);
            System.out.println(strMsg);
            int clientPort = dp.getPort();
            InetAddress clientAdress = dp.getAddress();
            dp = new DatagramPacket(new byte[length], length);
            dp.setAddress(clientAdress);
            dp.setPort(clientPort);
            dp.setData(strMsg.toUpperCase().getBytes());
            ds.send(dp);
        }
    }
}
