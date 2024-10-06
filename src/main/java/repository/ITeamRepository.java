package repository;

import java.sql.SQLException;
import java.util.*;

import entities.Team;

public interface ITeamRepository {
     public Boolean save(Team team) throws SQLException;
     public List<Team> getAllTeams(int from, int length) throws SQLException;
    public int getTeamCount() throws SQLException ;
    public Team getTeamById(Long id) throws SQLException;
    public Boolean deleteTeam(Long id)  throws SQLException;
}
