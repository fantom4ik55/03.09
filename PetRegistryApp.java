import java.sql.*;
import java.util.Scanner;

public class PetRegistryApp {
    private static Connection connect() {
        // MySQL connection setup
        String url = "jdbc:mysql://localhost:3306/Human_friends";
        String user = "root";
        String password = "yourpassword";
        
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    private static void printAllAnimals(Connection conn) {
        String query = "SELECT * FROM animal";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("Id");
                String className = rs.getString("Class_name");
                System.out.println("ID: " + id + ", Класс: " + className);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка животных: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection conn = connect()) {
            if (conn == null) {
                System.exit(-1);
            }

            while (true) {
                System.out.println("1. Завести новое животное");
                System.out.println("2. Определить животное в класс");
                System.out.println("3. Показать список команд животного");
                System.out.println("4. Обучить животное новым командам");
                System.out.println("5. Показать полный список животных");
                System.out.println("6. Выход");
                System.out.print("Выберите опцию: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (option) {
                    case 1:
                        // Logic to add a new animal
                        break;
                    case 2:
                        // Logic to classify an animal
                        break;
                    case 3:
                        // Logic to show animal commands
                        break;
                    case 4:
                        // Logic to teach new commands
                        break;
                    case 5:
                        printAllAnimals(conn);
                        break;
                    case 6:
                        System.out.println("Выход из программы.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Counter implements AutoCloseable {
    private int count = 0;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (count == 0) {
            throw new Exception("Counter not used within try-with-resources");
        }
    }
}
