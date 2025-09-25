

package com.wecp.progressive.dao;


import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {

    @Override
    public int addTeam(Team team) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO team (team_name, location, owner_name, establishment_year) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, team.getTeamName());
            statement.setString(2, team.getLocation());
            statement.setString(3, team.getOwnerName());
            statement.setInt(4, team.getEstablishmentYear());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                team.setTeamId(generatedID);
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
    public Team getTeamById(int teamId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM team WHERE team_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teamId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String teamName = resultSet.getString("team_name");
                String location = resultSet.getString("location");
                String ownerName = resultSet.getString("owner_name");
                int establishmentYear = resultSet.getInt("establishment_year");

                return new Team(teamId, teamName, location, ownerName, establishmentYear);
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
    public void updateTeam(Team team) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE team SET team_name = ?, location = ?, owner_name = ?, establishment_year = ? WHERE team_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, team.getTeamName());
            statement.setString(2, team.getLocation());
            statement.setString(3, team.getOwnerName());
            statement.setInt(4, team.getEstablishmentYear());
            statement.setInt(5, team.getTeamId());
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
    public void deleteTeam(int teamId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM team WHERE team_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teamId);
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
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM team";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int teamId = resultSet.getInt("team_id");
                String teamName = resultSet.getString("team_name");
                String location = resultSet.getString("location");
                String ownerName = resultSet.getString("owner_name");
                int establishmentYear = resultSet.getInt("establishment_year");
                teams.add(new Team(teamId, teamName, location, ownerName, establishmentYear));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return teams;
    }

}




