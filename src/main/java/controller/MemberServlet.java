package controller;


import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberModel;
import java.sql.*;
import java.util.List;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Member;
import enums.Role;
import repository.IMemberRepository;
import repository.impl.MemberRepositoryImpl;


public class MemberServlet  extends HttpServlet  {
    private IMemberRepository memberRepositoryImpl;
    private static final Logger logger = LoggerFactory.getLogger(MemberServlet.class);


    @Override
    public void init() throws ServletException {
        try {
            this.memberRepositoryImpl = new MemberRepositoryImpl();
            logger.info("MemberRepositoryImpl initialized successfully.");
        } catch (Exception e) {
            logger.error("Error initializing MemberServlet", e);
            throw new ServletException("Failed to initialize MemberRepositoryImpl", e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
           String action = req.getParameter("action");
            if (action == null || action.isEmpty()) {
                res.sendRedirect("member?action=list");
            } else if ("add".equalsIgnoreCase(action)) {
                create(req, res);
            } else if ("list".equalsIgnoreCase(action)) {
              index(req, res);
            } else if ("edit".equalsIgnoreCase(action)) {
                edit(req,res);
            }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("create".equals(action)) {
                Member member = new Member();
                member.setFirstName(req.getParameter("first_name"));
                member.setSecondName(req.getParameter("second_name"));
                member.setEmail(req.getParameter("email"));
                member.setUserRole(Role.valueOf(req.getParameter("role")));

                memberRepositoryImpl.create(member);


                res.sendRedirect("member?action=list");
            } else if ("update".equals(action)) {

                Member member = new Member();
                member.setId( Long.parseLong(req.getParameter("id")));
                member.setFirstName(req.getParameter("first_name"));
                member.setSecondName(req.getParameter("second_name"));
                member.setEmail(req.getParameter("email"));

                Member inserted = memberRepositoryImpl.updateMember(member);
                MemberModel memberModel = new MemberModel();


                if (inserted != null) {
                    memberModel.setSuccess("Member updated successfully");
                    req.setAttribute("model", memberModel);
                    res.sendRedirect("member?action=edit&id=" + member.getId());
                    return; 
                } else {
                    // RequestDispatcher dispatcher = req.getRequestDispatcher("errorPage.jsp");
                    // dispatcher.forward(req, res);
                    return;
                }




            } else if ("delete".equals(action)) {
                Long id =  Long.parseLong(req.getParameter("id"));
                Boolean deleted = memberRepositoryImpl.deleteMember(id);
                MemberModel memberModel = new MemberModel();

                if(deleted){
                    memberModel.setSuccess("Member updated successfully");
                    req.setAttribute("model", memberModel);
                    res.sendRedirect("member?action=list");
                }else{
                    // RequestDispatcher dispatcher = req.getRequestDispatcher("errorPage.jsp");
                    // dispatcher.forward(req, res);
                    return;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // GET REQUEST 
    protected void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Member> members = null;
        try {
            members = memberRepositoryImpl.getAllMembers();
        } catch (SQLException e) {
            logger.error("Error fetching the members", e);
        }
        MemberModel model = new MemberModel();
        model.setMembers(members);
		req.setAttribute("model", model);
		this.getServletContext().getRequestDispatcher("/views/member/index.jsp").forward(req, res);

	}

    protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/views/member/add.jsp").forward(req, res);
	}

    protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    {
        String memberId = req.getParameter("id");

            if (memberId != null && !memberId.isEmpty()) {
                try {
                    Long id = Long.parseLong(memberId);
                    Optional<Member> memberOptional = memberRepositoryImpl.findMemberById(id);
        
                    if (memberOptional.isPresent()) {
                        Member existingMember = memberOptional.get();


                        
                        Member newMember = new Member();
                        newMember.setId(existingMember.getId());
                        newMember.setFirstName(existingMember.getFirstName());
                        newMember.setSecondName(existingMember.getSecondName());
                        newMember.setEmail(existingMember.getEmail());

                        MemberModel model = new MemberModel();
                        model.setMember(newMember);

                        
                        req.setAttribute("model", model);
        
                        this.getServletContext().getRequestDispatcher("/views/member/edit.jsp").forward(req, res);
                    } else {
                        logger.error("Member not found for id: " + id);
                        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
                    }
                } catch (NumberFormatException | SQLException e) {
                    logger.error("Error fetching the member by id: " + memberId, e);
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Member ID");
                }
            } else {
                logger.error("ID parameter is missing");
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Member ID is required");
            }
        }
    }

}
