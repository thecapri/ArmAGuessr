package netz;

import game.GameControl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Enumeration;


public class Server implements Network{
    Socket client;
    GameControl gControl;

    public Server(int pRundenAnzahl){
        gControl = new GameControl(pRundenAnzahl, this);
        gControl.addToServerGUI("IP Adresse: "+getIPAdresse());
        System.out.println(getIPAdresse());
        gControl.setNetworkTitle("Server");
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            getIPAdresse();
            client = serverSocket.accept();
            addLenAndSendMessage(BigInteger.valueOf(gControl.getAnzahlRunden()).toByteArray());
            int[] zufall = gControl.selectRandomLocation(gControl.getAnzahlRunden());
            addLenAndSendMessage(convertIntegersToBytes(zufall));
            gControl.gameGUI(zufall);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLenAndSendMessage(byte[] message) {
        try {
            byte[] len = ByteBuffer.allocate(4)
                    .putInt(message.length)
                    .array();
            byte[] out = ByteBuffer.allocate(len.length + message.length)
                    .put(len)
                    .put(message)
                    .array();

            client.getOutputStream().write(out);
        } catch (IOException e) {
            gControl.addToServerGUI("Could not send Message to " + client.getInetAddress().getHostName());
        }
    }

    private byte[] getMessage() throws IOException {
        InputStream inputStream = client.getInputStream();
        byte[] len = new byte[4];
        if (!tryReadUntilBufferFull(inputStream, len))
            throw new IOException();
        byte[] message = new byte[len[0] << 24 | len[1] << 16 | len[2] << 8 | len[3]];
        if (!tryReadUntilBufferFull(inputStream, message))
            throw new IOException();

        return message;
    }
    public String getIPAdresse(){
        String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    return ip;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);

        }
        return "Fehler";
    }
    private boolean tryReadUntilBufferFull(InputStream inputStream, byte[] buffer) {
        if (buffer.length == 0)
            return false;
        try {
            int read = 0;
            while (read < buffer.length) {
                int readBytes = inputStream.read(buffer, read, buffer.length - read);
                if (readBytes == -1)
                    return false;
                read += readBytes;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private byte[] convertIntegersToBytes (int[] integers) {
        if (integers != null) {
            byte[] outputBytes = new byte[integers.length * 4];

            for(int i = 0, k = 0; i < integers.length; i++) {
                int integerTemp = integers[i];
                for(int j = 0; j < 4; j++, k++) {
                    outputBytes[k] = (byte)((integerTemp >> (8 * j)) & 0xFF);
                }
            }
            return outputBytes;
        } else {
            return null;
        }
    }
    public int[] byte2int(byte[]src) {
        int dstLength = src.length >>> 2;
        int[]dst = new int[dstLength];

        for (int i=0; i<dstLength; i++) {
            int j = i << 2;
            int x = 0;
            x += (src[j++] & 0xff) << 0;
            x += (src[j++] & 0xff) << 8;
            x += (src[j++] & 0xff) << 16;
            x += (src[j++] & 0xff) << 24;
            dst[i] = x;
        }
        return dst;
    }


    @Override
    public void sendPoints(int pPoints) {
        addLenAndSendMessage(BigInteger.valueOf(pPoints).toByteArray());
    }

    @Override
    public int receivePoints() {
        try {
            return new BigInteger(getMessage()).intValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendCoords(int[] pCoords) {
        addLenAndSendMessage(convertIntegersToBytes(pCoords));
    }

    @Override
    public int[] receiveCoords() {
        try {
            return byte2int(getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
