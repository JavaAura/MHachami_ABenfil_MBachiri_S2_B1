package model;

public class TeamRequest {
    private String teamName;
    private String[] membersId;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String[] getMembersId() {
        return membersId;
    }

    public void setMembersId(String[] membersId) {
        this.membersId = membersId;
    }
}
