package model;

import java.util.ArrayList;
import java.util.List;

import entities.Team;

public class TeamModel {
      private String motCle;
    private Team team = new Team();
    private List<Team> teams = new ArrayList<>();
    private String error;
    private String success;
    private int totalTeams;
    private int page;
    private long pageNumbers;
    private int pageSize;



    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getTeams() {
        return teams;
    }

  

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotalTeams() {
        return totalTeams;
    }

    public void setTotalTeams(int totalTeams) {
        this.totalTeams = totalTeams;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(long pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
