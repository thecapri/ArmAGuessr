 import java.awt.*;
 import java.sql.*;

    public class DataBase {
        private Connection con;

        /**
         * Erstellt die Datenbank
         */
        public DataBase() {
            String DB = "CREATE TABLE IF NOT EXISTS ArmaGuessr ("
                    + "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "Beschreibung Text,"
                    + "SaveLocation Text,"
                    + "pX INT,"
                    + "pY INT);";
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:ArmaGuessr.db");
                Statement stmt = con.createStatement();
                System.out.println("Connection to Database successful");
                stmt.executeUpdate(DB);
                stmt.close();
            } catch (ClassNotFoundException|SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Speichert einen neuen Ort in die Datenbank
         * @param pBeschreibung Beschreibung des Ortes
         * @param pSaveLocation Name des Speicherortes
         * @param pX X Koordinate auf der Map
         * @param pY Y Koordinate auf der Map
         */

        public void saveNewLocation(String pBeschreibung, String pSaveLocation, int pX, int pY){

            String sqlInsert = "INSERT INTO ArmaGuessr"
                    + "(Beschreibung, SaveLocation, pX, pY)"
                    + "VALUES ("  + "\"" + pBeschreibung + "\",\""+ pSaveLocation + "\"," + pX + "," + pY  + ");";
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sqlInsert);
                stmt.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }


        /**
         * Gibt den speicher Ort zurück
         * @param pGenerationNummer ID des Ortes der Ausgelesen werden soll
         * @return Rückgabe Speicherort als String
         */

        public String readSaveLocation(int pGenerationNummer){
            String pSaveLocation = null;
            try{
                String sqlQuery = "SELECT SaveLocation FROM ArmaGuessr"
                        + " WHERE ID ="+pGenerationNummer+ ";";
                Statement stmt = con.createStatement();
                ResultSet table = stmt.executeQuery(sqlQuery);
                pSaveLocation = table.getString("SaveLocation");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("Datenbank Speicherort: "+pSaveLocation);
            return pSaveLocation;
        }

        /**
         * Gibt den Namen zurück
         * @param pGenerationNummer ID des Ortes der Ausgelesen werden soll
         * @return Rückgabe Beschreibung als String
         */
        public String readName(int pGenerationNummer){
            String pBeschreibung = null;
            try{
                String sqlQuery = "SELECT Beschreibung FROM ArmaGuessr"
                        + " WHERE ID ="+pGenerationNummer+ ";";
                Statement stmt = con.createStatement();
                ResultSet table = stmt.executeQuery(sqlQuery);
                pBeschreibung = table.getString("Beschreibung");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("-Datenbank- Location: "+pBeschreibung);
            return pBeschreibung;
        }

        /**
         * Gibt die X und Y Koordinate des Ortes auf der Map zurück
         * @param pGenerationNummer ID des Ortes der Ausgelesen werden soll
         * @return Rückgabe Koordinaten als Array X=[0], Y=[1]
         */
        public Point readXandY(int pGenerationNummer){
            Point pOrtLocation = new Point();
            try{
                String sqlQuery = "SELECT pY, pX FROM ArmaGuessr"
                        + " WHERE ID ="+pGenerationNummer+";";
                Statement stmt = con.createStatement();
                ResultSet table = stmt.executeQuery(sqlQuery);
                pOrtLocation.setLocation(table.getInt("pX"),table.getInt("pY"));
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            //System.out.println("Datenbank Location "+pGenerationNummer+": X: "+pOrtLocation.getX()+", Y: "+pOrtLocation.getY());
            return pOrtLocation;
        }

        /**
         * Löscht eine bestimmte Zeile aus der Datenbank
         * @param pGenerationNummer ID der Zeile
         */
        public void deleteRow(int pGenerationNummer){
            try{
                Statement stmt = con.createStatement();
                stmt.executeUpdate("DELETE FROM ArmaGuessr\n" +
                        "WHERE ID ="+ pGenerationNummer + ";");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        /**
         * Löscht alle Einträge der DB
         */
        public void clearDB(){
            try{
                Statement stmt = con.createStatement();
                stmt.executeUpdate("DELETE from ArmaGuessr");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
