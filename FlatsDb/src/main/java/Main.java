import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/FlatsDb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";

    static Connection conn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();

                while (true) {
                    System.out.println("1: add flat");
                    System.out.println("2: add random flats");
                    System.out.println("3: delete flat");
                    System.out.println("4: change flat");
                    System.out.println("5: view flats");
                    System.out.println("6: select flats by district");
                    System.out.println("7: select flats by district and area");
                    System.out.println("8: select flats by area and price");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            insertRandomFlats(sc);
                            break;
                        case "3":
                            deleteFlat(sc);
                            break;
                        case "4":
                            changeFlat(sc);
                            break;
                        case "5":
                            viewFlats();
                            break;
                        case "6":
                            selectByDistrict(sc);
                            break;
                        case "7":
                            selectByDistArea(sc);
                            break;
                        case "8":
                            selectByAreaPrice(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Flats");

            st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR (30) NOT NULL, " +
                    "address VARCHAR (50) NOT NULL, rooms INT NOT NULL, area DECIMAL(10,2) NOT NULL, " +
                    " price DECIMAL (10,2) NOT NULL)");
        } finally {
            st.close();
        }
    }

    private static void addFlat(Scanner sc) throws SQLException {
        System.out.print("Enter the district name: ");
        String district = sc.nextLine();
        System.out.print("Enter the address: ");
        String address = sc.nextLine();
        System.out.println("Enter the number of rooms: ");
        String sRooms = sc.nextLine();
        System.out.println("Enter the size of the apartment: ");
        String sArea = sc.nextLine();
        System.out.println("Enter the price: ");
        String sPrice = sc.nextLine();

        int rooms = Integer.parseInt(sRooms);
        double area = Double.parseDouble(sArea);
        double price = Double.parseDouble(sPrice);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Flats (district, address, rooms, area, price) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, district);
            ps.setString(2, address);
            ps.setInt(3, rooms);
            ps.setDouble(4, area);
            ps.setDouble(5, price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void deleteFlat(Scanner sc) throws SQLException {
        System.out.print("Enter the flat ID: ");
        String sId = sc.nextLine();
        int id = Integer.parseInt(sId);

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Flats WHERE id = ?");
        try {
            ps.setInt(1, id);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void changeFlat(Scanner sc) throws SQLException {
        System.out.print("Enter the address of flat: ");
        String address = sc.nextLine();
        System.out.println("Enter the size of the apartment: ");
        String sArea = sc.nextLine();
        double area = Double.parseDouble(sArea);

        PreparedStatement ps = conn.prepareStatement("UPDATE Flats SET area = ? WHERE address = ?");
        try {
            ps.setDouble(1, area);
            ps.setString(2, address);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void insertRandomFlats(Scanner sc) throws SQLException {
        System.out.print("Enter flats count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);
        Random rnd = new Random();
        String[] district = {"Darnytsky", "Obolonskyi", "Podilskyi", "Shevchenkivskyi", "Sviatoshynskyi"};
        String[] address = {"Zhilyanskaya st., 118", " Polovetskaya st., 7/13", "Rollana R. b-r, 5b",
                            " Sadovaya st., 10a", " Pravdy pr-t, 3", " Krasnopolskaya st., 2b",
                            "Urlovskaya st., 23g", "Avtozavodskaya st., 29"};

        conn.setAutoCommit(false); // enable transactions
        try {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Flats (district, address, rooms, area, price) VALUES(?, ?, ?, ?, ?)");
                try {
                    for (int i = 0; i < count; i++) {
                        ps.setString(1, district[rnd.nextInt(district.length)]);
                        ps.setString(2, address[rnd.nextInt(address.length)]);
                        ps.setInt(3, rnd.nextInt(5) + 1);
                        ps.setDouble(4, rnd.nextDouble() * 200 + 20);
                        ps.setDouble(5, rnd.nextDouble() * 3000000 + 700000);
                        ps.executeUpdate();
                    }
                    conn.commit();
                } finally {
                    ps.close();
                }
            } catch (Exception ex) {
                conn.rollback();
            }
        } finally {
            conn.setAutoCommit(true); // return to default mode
        }
    }

    private static void viewFlats() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats");
        try {
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void selectByDistrict(Scanner sc) throws SQLException {
        System.out.print("Enter district: ");
        String district = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats WHERE district = ?");
        try {
            ps.setString(1, district);
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void selectByDistArea(Scanner sc) throws SQLException {
        System.out.print("Enter district: ");
        String district = sc.nextLine();
        System.out.println("Enter the min size of the apartment: ");
        String sMinArea = sc.nextLine();
        double minArea = Double.parseDouble(sMinArea);
        System.out.println("Enter the max size of the apartment: ");
        String sMaxArea = sc.nextLine();
        double maxArea = Double.parseDouble(sMaxArea);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats WHERE district = ? AND area >= ? AND area <= ?");
        try {
            ps.setString(1, district);
            ps.setDouble(2, minArea);
            ps.setDouble(3, maxArea);
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void selectByAreaPrice(Scanner sc) throws SQLException {
        System.out.println("Enter the min size: ");
        String sMinArea = sc.nextLine();
        double minArea = Double.parseDouble(sMinArea);
        System.out.println("Enter the max size: ");
        String sMaxArea = sc.nextLine();
        double maxArea = Double.parseDouble(sMaxArea);
        System.out.println("Enter the min price: ");
        String sMinPrice = sc.nextLine();
        double minPrice = Double.parseDouble(sMinPrice);
        System.out.println("Enter the max price: ");
        String sMaxPrice = sc.nextLine();
        double maxPrice = Double.parseDouble(sMaxPrice);

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats WHERE area >= ? AND area <= ? AND price >= ? AND price <= ?");
        try {
            ps.setDouble(1, minArea);
            ps.setDouble(2, maxArea);
            ps.setDouble(3, minPrice);
            ps.setDouble(4, maxPrice);
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void print(ResultSet rs) throws SQLException {
        try {
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++){
                System.out.print(md.getColumnName(i) + "\t\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } finally {
            rs.close();
        }
    }
}
