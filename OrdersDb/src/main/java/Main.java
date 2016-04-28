import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/OrdersDb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "6134469r";

    static Connection conn;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();

                while (true) {
                    System.out.println("1: add new product");
                    System.out.println("2: add new client");
                    System.out.println("3: add new order");
                    System.out.println("4: view products");
                    System.out.println("5: view clients");
                    System.out.println("6: view orders");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addProduct(sc);
                            break;
                        case "2":
                            addClient(sc);
                            break;
                        case "3":
                            addOrder(sc);
                            break;
                        case "4":
                            viewProducts();
                            break;
                        case "5":
                            viewClients();
                            break;
                        case "6":
                            viewOrders();
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
            st.execute("DROP TABLE IF EXISTS Products");
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("DROP TABLE IF EXISTS Orders");
            st.execute("CREATE TABLE Products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, prodName VARCHAR(100) NOT NULL, price INT NOT NULL)");
            st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, clientName VARCHAR(100) NOT NULL, phone VARCHAR(20) NOT NULL)");
            st.execute("CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, pId INT NOT NULL, cId INT NOT NULL, info VARCHAR(100))");
        } finally {
            st.close();
        }
    }

    private static void addProduct(Scanner sc) throws SQLException {
        System.out.print("Enter the product name: ");
        String prodName = sc.nextLine();
        System.out.println("Enter the price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Products (prodName, price) VALUES(?, ?)");
        try {
            ps.setString(1, prodName);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void addClient(Scanner sc) throws SQLException {
        System.out.print("Enter the client name: ");
        String clientName = sc.nextLine();
        System.out.println("Enter phone: ");
        String phone = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Clients (clientName, phone) VALUES(?, ?)");
        try {
            ps.setString(1, clientName);
            ps.setString(2, phone);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void addOrder(Scanner sc) throws SQLException {
        System.out.print("Enter the product name: ");
        String prodName = sc.nextLine();
        System.out.println("Enter price: ");
        String sPrice = sc.nextLine();
        System.out.println("Enter the client name");
        String clientName = sc.nextLine();
        System.out.println("Enter phone");
        String phone = sc.nextLine();
        System.out.println("Enter the info");
        String info = sc.nextLine();
        double price = Double.parseDouble(sPrice);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders (pId, cId, info)" +
                " VALUES((SELECT id FROM Products WHERE prodName = ? AND price = ?)," +
                "(SELECT id FROM Clients WHERE clientName = ? AND phone = ?), ?)");
        try {
            ps.setString(1, prodName);
            ps.setDouble(2, price);
            ps.setString(3, clientName);
            ps.setString(4, phone);
            ps.setString(5, info);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void viewProducts() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products");
        try {
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void viewClients() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Clients");
        try {
            ResultSet rs = ps.executeQuery();
            print(rs);
        } finally {
            ps.close();
        }
    }

    private static void viewOrders() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Orders");
        try {
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
