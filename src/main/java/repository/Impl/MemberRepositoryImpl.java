package repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.mysql.cj.xdevapi.Client;

import utils.DatabaseConnection;
import enums.Role;
import repository.IMemberRepository;
import entities.Member;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MemberRepositoryImpl implements IMemberRepository {
     private static Connection con = DatabaseConnection.getConnection();
    private static final Logger logger = LoggerFactory.getLogger(MemberRepositoryImpl.class);
    
    @Override
    public boolean create(Member member){
        if (con == null) {
            logger.error("database connection is not set");
            return false;
        }  

         String query = "INSERT INTO Member(first_name,second_name,email,user_role) VALUES (?, ?, ?, ?)";
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
        Member member = new Member();
        String query = "SELECT * FROM Member WHERE id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                member.setId(rs.getLong("id"));
                member.setFirstName(rs.getString("first_name"));
                member.setSecondName(rs.getString("second_name"));
                member.setEmail(rs.getString("email"));
                member.setUserRole(Role.valueOf(rs.getString("user_role")));

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

    // List<Member> getAllMembers(){
    //     logger.info("hell");

    // }




}
