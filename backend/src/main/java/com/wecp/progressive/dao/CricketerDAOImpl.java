package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Cricketer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CricketerDAOImpl implements CricketerDAO {

    @Override
    public int addCricketer(Cricketer cricketer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO cricketer (team_id, cricketer_name, age, nationality, experience, role, total_runs, total_wickets) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, cricketer.getTeam().getTeamId());
            statement.setString(2, cricketer.getCricketerName());
            statement.setInt(3, cricketer.getAge());
            statement.setString(4, cricketer.getNationality());
            statement.setInt(5, cricketer.getExperience());
            statement.setString(6, cricketer.getRole());
            statement.setInt(7, cricketer.getTotalRuns());
            statement.setInt(8, cricketer.getTotalWickets());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                cricketer.setCricketerId(generatedID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return generatedID;
    }


    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM cricketer WHERE cricketer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cricketerId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int teamId = resultSet.getInt("team_id");
                String cricketerName = resultSet.getString("cricketer_name");
                int age = resultSet.getInt("age");
                String nationality = resultSet.getString("nationality");
                int experience = resultSet.getInt("experience");
                String role = resultSet.getString("role");
                int totalRuns = resultSet.getInt("total_runs");
                int totalWickets = resultSet.getInt("total_wickets");

                return new Cricketer(cricketerId, teamId, cricketerName, age, nationality, experience, role, totalRuns, totalWickets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE cricketer SET team_id = ?, cricketer_name = ?, age = ?, nationality = ?, experience = ?, role = ?, total_runs = ?, total_wickets = ? WHERE cricketer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cricketer.getTeam().getTeamId());
            statement.setString(2, cricketer.getCricketerName());
            statement.setInt(3, cricketer.getAge());
            statement.setString(4, cricketer.getNationality());
            statement.setInt(5, cricketer.getExperience());
            statement.setString(6, cricketer.getRole());
            statement.setInt(7, cricketer.getTotalRuns());
            statement.setInt(8, cricketer.getTotalWickets());
            statement.setInt(9, cricketer.getCricketerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM cricketer WHERE cricketer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cricketerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        List<Cricketer> cricketers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM cricketer";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cricketerId = resultSet.getInt("cricketer_id");
                int teamId = resultSet.getInt("team_id");
                String cricketerName = resultSet.getString("cricketer_name");
                int age = resultSet.getInt("age");
                String nationality = resultSet.getString("nationality");
                int experience = resultSet.getInt("experience");
                String role = resultSet.getString("role");
                int totalRuns = resultSet.getInt("total_runs");
                int totalWickets = resultSet.getInt("total_wickets");
                cricketers.add(new Cricketer(cricketerId, teamId, cricketerName, age, nationality, experience, role, totalRuns, totalWickets));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return cricketers;
    }

}