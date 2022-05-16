package netz;

public interface Network {
    public void sendPoints(int pPoints);
    public int receivePoints();
    public void sendCoords(int[] pCoords);
    public int[] receiveCoords();
}
