package repository.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Member;
import repository.IMemberRepository;
import utils.DatabaseConnection;
import enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;
 

public class MemberRepositoryImpl implements IMemberRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(MemberRepositoryImpl.class);
    private final Connection con;

    public MemberRepositoryImpl() throws SQLException {
        loadDriver();
        this.con = DatabaseConnection.getConnection(); 
    }
  

    @Override
    public boolean create(Member member){
        if (con == null) {
            logger.error("database connection is not set");
            return false;
        }  
         String query = "INSERT INTO members(first_name,last_name,email,role) VALUES (?, ?, ?, ?)";
        PreparedStatement ps=null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, member.getFirstName());
            ps.setString(2, member.getSecondName());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getUserRole().name());
            int n = ps.executeUpdate();
            
            return n ==1;
        } catch (SQLException e) {
            logger.error("error in create function " , e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error("error in create function " , e);
                }
            }
        }
        return false; 
    }
  
    @Override
    public Optional <Member> findMemberById(Long id){
        if (con == null) {
            logger.error("database connection is not set");
            return null;
        }  
        Member member = new Member();
        String query = "SELECT * FROM members WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                member.setId(rs.getLong("id"));
                member.setFirstName(rs.getString("first_name"));
                member.setSecondName(rs.getString("last_name"));
                member.setEmail(rs.getString("email"));
                member.setUserRole(Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            logger.error("error in findMemberById function " , e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                logger.error("error in findMemberById function " , e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error("error in findMemberById function " , e);
                }
            }
        }
        return Optional.ofNullable(member); 
    }


    @Override
    public List<Member> getAllMembers() throws SQLException {
        if (con == null) {
            logger.error("database connection is not set");
            return null;
        }  
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getLong("id"));
                member.setFirstName(resultSet.getString("first_name"));
                member.setSecondName(resultSet.getString("last_name"));
                member.setEmail(resultSet.getString("email"));
                member.setUserRole(Role.valueOf(resultSet.getString("role"))); 
                members.add(member);
            }
            logger.info("Retrieved all members. Total count: {}", members.size());
        } catch (SQLException e) {
            logger.error("Error retrieving all members", e);
            throw e; 
        }
        return members; 
    }


    @Override
    public Member updateMember(Member member) throws SQLException {

        if (con == null) {
            logger.error("database connection is not set");
            return null;
        }  

        String sql = "UPDATE members SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getSecondName());
            statement.setString(3, member.getEmail());
            statement.setLong(4, member.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Updated member: {}", member);
                return member; 
            }
            logger.warn("Update failed, no member found with ID: {}", member.getId());
            return null; 
        } catch (SQLException e) {
            logger.error("Error updating member: {}", member, e);
            throw e; 
        }
    }


    @Override
    public Boolean deleteMember(Long id) throws SQLException {
        if (con == null) {
            logger.error("database connection is not set");
            return null;
        }  
         String sql = "DELETE FROM members WHERE id = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, id);
            boolean isDeleted = statement.executeUpdate() > 0; 
            if (isDeleted) {
                logger.info("Deleted member with ID: {}", id);
            } else {
                logger.warn("No member found to delete with ID: {}", id);
            }
            return isDeleted;
        } catch (SQLException e) {
            logger.error("Error deleting member with ID: {}", id, e);
            throw e; 
        }
    }

    private void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
