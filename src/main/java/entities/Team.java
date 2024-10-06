package entities;

import java.util.*;

public class Team {

    private Long id;
    private String name;
    private List<Member> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers(){
        return this.members;
    }

    public void setMembers(List<Member> members){
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", name=" + name + "]";
    }
}
