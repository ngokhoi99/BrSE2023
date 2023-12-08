package com.vti.backend.repository;
import com.vti.entity.User;
import com.vti.utils.JBDCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private JBDCUtils jdbcUtils;

    public Repository() {
        // TODO Auto-generated constructor stub
        jdbcUtils = new JBDCUtils();
    }


    @Override
    public List<User> getListUser() throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        Connection connection = jdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        List<User> listUser = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("pro_skill"));
            listUser.add(user);
        }
        return listUser;
    }

    @Override
    public List<User> getUser(int id) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        Connection connection = jdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM User where id = " + id);
        List<User> idUser = new ArrayList<User>();
        while (resultSet.next()) {
            idUser.add(
                new User(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("pro_skill"))
                );
            }
        return idUser;
    }
    @Override
    public void deleteUser(int id) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
        Connection connection = jdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE  FROM user where  id = " + id);

    }

    @Override
    public User isLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection = jdbcUtils.getConnection();

        String sql = "SELECT * FROM user WHERE `email`=? AND `password`=?";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("pro_skill")
            );

            return user;
        } else {
            return null;
        }
    }


    @Override
    public void createEmployee(String fullName, String email) throws SQLException, ClassNotFoundException {
        Connection connection =jdbcUtils.getConnection();
        String sql = "INSERT INTO user (full_name, email) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, email);

        int num = preparedStatement.executeUpdate();
        if (num < 0) {
            System.out.println("Tạo employee thất bại!");
        }
    }


    @Override
    public boolean isUserExists(String email) throws SQLException, ClassNotFoundException {
        Connection connection = jdbcUtils.getConnection();
        String sql = "SELECT 1 FROM user WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }
}