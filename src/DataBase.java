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
                    + "VALUES (" + pID + ",\"" + pBeschreibung + ",\""+ pSaveLocation + ",\"" + pX + ",\"" + pY + "\"" + ");";
            try {
                Statement stmt = con.createStatement();
                stmt.execute(sqlInsert);
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
            System.out.println("Location: "+pSaveLocation);
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
            System.out.println("Beschreibung: "+pBeschreibung);
            return pBeschreibung;
        }

        /**
         * Gibt die X und Y Koordinate des Ortes auf der Map zurück
         * @param pGenerationNummer ID des Ortes der Ausgelesen werden soll
         * @return Rückgabe Koordinaten als Array X=[0], Y=[1]
         */
        public int[] readXandY(int pGenerationNummer){
            int[] pOrtLocation = null;
            try{
                String sqlQuery = "SELECT pY, pX FROM ArmaGuessr"
                        + " WHERE ID ="+pGenerationNummer+";";
                Statement stmt = con.createStatement();
                ResultSet table = stmt.executeQuery(sqlQuery);
                pOrtLocation[0] = table.getInt("pX");
                pOrtLocation[1] = table.getInt("pY");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("X: "+pOrtLocation[0]);
            System.out.println("Y: "+pOrtLocation[1]);
            return pOrtLocation;
        }

        /**
         * Löscht alle Einträge der DB
         */
        /**
        public void clearDB(){
            try{
                Statement stmt = con.createStatement();
                stmt.executeUpdate("DELETE from GAMEOFLIFE");
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        **/
    }
