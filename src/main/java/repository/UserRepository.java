package repository;

import db.ConnectHSQLDB;
import domain.User;
import domain.Privilege;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository {

    private static final String FIND_STMT = "SELECT username, password, email, usertype FROM users WHERE username=?";
    private static final String INSER_STMT = "INSERT INTO users(username, password, email, usertype) VALUES(?,?,?,?)";
    private static final String SELECT_STMT = "SELECT username, password, email, usertype FROM USERS";
    private static final String UPDATE_STMT = "UPDATE users SET usertype = 'PREMIUM' WHERE username = (?)";
    private static final  String TABLE_NAME = "users";
    private static final  String CREATE_TABLE = "CREATE TABLE users(" +
            " username varchar(20), " +
            "password varchar(20), " +
            "email varchar(20), " +
            "usertype varchar(20)" +
            ")";
    private Connection connection = new ConnectHSQLDB().getConnection();

    public UserRepository() {
        checkIfTableExsistAndCreateTable(connection,TABLE_NAME, CREATE_TABLE);
    }
 @Override
    public User getUserByName(String name) {
        PreparedStatement findStatement = null;
        User user = new User();
        try{
            findStatement = connection.prepareStatement(FIND_STMT);
            findStatement.setString(1, name);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            user = load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
 }

    @Override
    public void addUser(User user) {
        PreparedStatement addStatement = null;
        try{
            addStatement = connection.prepareStatement(INSER_STMT);
            parametrizeInsertStatement(addStatement,user);
            addStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try{
             stmt = connection.createStatement();
             rs = stmt.executeQuery(SELECT_STMT);

             while (rs.next()){
                 userList.add(load(rs));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userList;
    }

    @Override
    public void addPremiumPrivilege(String username) {
        PreparedStatement updateStatement = null;
        try{
            updateStatement = connection.prepareStatement(UPDATE_STMT);
            parametrizeUpdateStatement(updateStatement, username);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parametrizeUpdateStatement(PreparedStatement updateStatement, String username) throws SQLException {
        updateStatement.setString(1, username);
    }
    private void parametrizeInsertStatement(PreparedStatement statement, User user) throws SQLException {
     statement.setString(1,user.getUsername());
     statement.setString(2, user.getPassword());
     statement.setString(3, user.getEmail());
     statement.setString(4, user.getUsertype().name());
    }

    private User load(ResultSet rs) throws SQLException {
     User user = new User();
     user.setUsername(rs.getString("username"));
     user.setPassword(rs.getString("password"));
     user.setEmail(rs.getString("email"));
     user.setUsertype(Privilege.valueOf(rs.getString("usertype")));
     return user;
    }

    private void checkIfTableExsistAndCreateTable(Connection connection, String tableName, String createTable) {
     try {
         ResultSet rs = connection.getMetaData().getTables(null,null,null, null);
         boolean tabelExsisting = false;

         while(rs.next()) {
             if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName)){
                 tabelExsisting = true;
                 break;
             }
         }
         if(!tabelExsisting) {
             Statement createTableStmt = connection.createStatement();
             createTableStmt.executeUpdate(createTable);
         }
     } catch(SQLException e) {
         e.printStackTrace();
     }
    }
}
