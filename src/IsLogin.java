//
//import java.sql.*;
//
//public class IsLogin {
//
//    public static boolean isLogin(String username, String password) {
//        String mql_url = "jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8";
//        String mql_user = "root";
//        String mql_password = "/*-w123l/*-";
//        String sql = "select username ,password from manager where username = ? and password = ?";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(mql_url, mql_user, mql_password);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setObject(1, username);
//            preparedStatement.setObject(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                return true;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//}
