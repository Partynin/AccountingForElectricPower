import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnectToDB {

    private Connection DBConnection;

    /** Метод для получения соединения с БД */
    public Connection getDBConnection() {
        try {
            // Загрузка JDBC driver - этот драйвер использует джава для содединения с постгрес
            Class.forName("org.postgresql.Driver");
            // Соединяемся с базой данных
            DBConnection = DriverManager.getConnection
                    ("jdbc:postgresql://balarama.db.elephantsql.com/fbxkjxci", "fbxkjxci",
                            "BeLgcnKykF5xfpvEYIpJaaYzzxAq40Bn");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in method getDBConnection! Can not connect to DB!");
            e.printStackTrace();
        }

        return DBConnection;
    }

    /** Данный метод возвращает список с названиями городов */
    public List<String> getCityTitles() {
        List<String> cityTitles = new ArrayList<>();
        try {
            // Создаём statement - сесию в рамках которой будем работать с БД
            Statement statement = getDBConnection().createStatement();
            // Используя statement выполняем запрос к нашей БД и записываем результат в переменную resultSet
            ResultSet resultSet = statement.executeQuery("select * from juliy.\"cCity\"");
            // В цикле выводим полученые результаты в терминал
            while (resultSet.next()) {
                String idCity = resultSet.getString(1);
                String titleCity = resultSet.getString(2);
                System.out.println(idCity + "\t" + titleCity);
                // записываем названия городов в ArrayList
                cityTitles.add(titleCity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityTitles;
    }

    /** Данный метод возвращает список с названиями улиц*/
    public List<String> getStreetTitles() {
        List<String> streetTitles = new ArrayList<>();
        try {
            Statement statement = getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from juliy.\"CStreet\"");
            while (resultSet.next()) {
                String idStreet = resultSet.getString(1);
                String titleStreet = resultSet.getString(3);
                System.out.println(idStreet + "\t" + titleStreet);
                streetTitles.add(titleStreet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streetTitles;
    }

    /** Данный метод возвращает список с номерами домов */
    public List<String> getHouseTitles() {
        List<String> houseTitles = new ArrayList<>();
        try {
            Statement statement = getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from juliy.\"cHouse\"");
            while (resultSet.next()) {
                String idHouse = resultSet.getString(1);
                String titleHouse = resultSet.getString(4);
                System.out.println(idHouse + "\t" + titleHouse);
                houseTitles.add(titleHouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseTitles;
    }

    /** Данный метод возвращает список с номерами квартир */
    public List<String> getFlatTitles() {
        List<String> flatTitles = new ArrayList<>();
        try {
            Statement statement = getDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from juliy.\"cFlat\"");
            while (resultSet.next()) {
                String idFlat = resultSet.getString(1);
                String titleFlat = resultSet.getString(5);
                System.out.println(idFlat + "\t" + titleFlat);
                flatTitles.add(titleFlat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flatTitles;
    }

    /** Метод возвращает найденых пользователей */
    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM juliy.users WHERE login = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = getDBConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error in method getUser!");
            e.printStackTrace();
        }

        return resultSet;
    }
}
