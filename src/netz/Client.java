package netz;

import game.GameControl;
import game.StartGUI;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client implements Network{
    Socket server;
    GameControl gControl;


    public Client(String pIPAdresse, StartGUI sGUI){
        server = new Socket();
        System.out.println(pIPAdresse);
        try {
            server.connect(new InetSocketAddress(pIPAdresse, 5000));
            gControl = new GameControl(new BigInteger(getMessage()).intValue(), this);
            sGUI.setGameControl(gControl);
            int[] zufall = byte2int(getMessage());
            gControl.gameGUI(zufall);
            gControl.setNetworkTitle("Client");
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
            server.getOutputStream().write(out);
        } catch (IOException e) {
            gControl.addToServerGUI("Could not send Message to " + server.getInetAddress().getHostName());
        }
    }

    private byte[] getMessage() throws IOException {
        InputStream inputStream = server.getInputStream();
        byte[] len = new byte[4];
        if (!tryReadUntilBufferFull(inputStream, len))
            throw new IOException();
        byte[] message = new byte[len[0] << 24 | len[1] << 16 | len[2] << 8 | len[3]];
        if (!tryReadUntilBufferFull(inputStream, message))
            throw new IOException();

        return message;
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