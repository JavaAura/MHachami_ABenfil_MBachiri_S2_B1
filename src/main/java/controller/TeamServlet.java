package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;



import entities.Member;
import entities.Team;
import model.MemberModel;
import model.TeamModel;
import model.TeamRequest;
import repository.IMemberRepository;
import repository.ITeamRepository;
import repository.impl.MemberRepositoryImpl;
import repository.impl.TeamRepositoryImpl;

public class TeamServlet extends HttpServlet {
    
    private IMemberRepository memberRepositoryImpl;
    private ITeamRepository teamRepository;

    private static final Logger logger = LoggerFactory.getLogger(MemberServlet.class);

    @Override
    public void init() throws ServletException  {
          try {
            this.memberRepositoryImpl = new MemberRepositoryImpl();
            this.teamRepository = new TeamRepositoryImpl();
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
            if ("create".equals(action)) {
                createP(req,res);
            } else if ("update".equals(action)) {
            } else if ("delete".equals(action)) {
                delete(req,res);
            }
        
    }

    // GET REQUEST 
    protected void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pageString = req.getParameter("page");
        TeamModel model = new TeamModel();
		int page = 1;
		if (pageString != null && pageString.matches("-?\\d+(\\.\\d+)?"))
        page = Integer.parseInt(pageString);

        try {
        int totalTeams = teamRepository.getTeamCount();
        int pageSize = 3;
		long pageNumbers = (totalTeams + pageSize - 1) / pageSize;


        

            int from = 0;
            int length = 3;

            if (page > 1) {
                from = length * (page - 1);
            }

            List<Team> teams = teamRepository.getAllTeams(from, length);
            model.setTeams(teams);
            model.setPage(page);
            model.setPageSize(pageSize);
            model.setPageNumbers(pageNumbers);
            model.setTotalTeams(totalTeams);

            
        } catch (Exception e) {
            logger.error("Error ", e);
        }
		req.setAttribute("model", model);

		this.getServletContext().getRequestDispatcher("/views/team/index.jsp").forward(req, res);

	}

    protected void create(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MemberModel model = new MemberModel();
        try {
            List<Member> members = memberRepositoryImpl.getAllMembers();
            model.setMembers(members);
            
        } catch (Exception e) {
            logger.error("Error ", e);
        }
		req.setAttribute("model", model);
		this.getServletContext().getRequestDispatcher("/views/team/add.jsp").forward(req, res);
	}

    protected void createP(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TeamRequest teamRequest = objectMapper.readValue(sb.toString(), TeamRequest.class);

        String teamName = teamRequest.getTeamName();
        String[] membersId = teamRequest.getMembersId();

        logger.info("Team Name in servlet: " + teamName);


            List<String> membersList = new ArrayList<>();
            if (membersId != null) { 
                membersList.addAll(Arrays.asList(membersId));
            }


            Team team = new Team();
            List<Member> members = new ArrayList<>();

            for (String memberId : membersList) {
                Long id = Long.valueOf(memberId); 
                Member member = new Member();
                member.setId(id);
                members.add(member);
            }
            team.setMembers(members);
            team.setName(teamName);

            Boolean add = false;
            try {
                add = this.teamRepository.save(team);
                 // Send a success response
                res.setStatus(HttpServletResponse.SC_CREATED);
                res.getWriter().write("Team created successfully.");
            } catch (SQLException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            }
    }

    protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        String teamId = req.getParameter("id");
        
        if (teamId != null && !teamId.isEmpty()) {
            try {
                Long id = Long.parseLong(teamId);
                Team team = teamRepository.getTeamById(id);
    
                TeamModel model = new TeamModel();
                model.setTeam(team);


                    
                req.setAttribute("model", model);
    
                this.getServletContext().getRequestDispatcher("/views/team/edit.jsp").forward(req, res);
            } catch (NumberFormatException | SQLException e) {
                logger.error("Error fetching the team by id: " + teamId, e);
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid team ID");
            }
        } else {
            logger.error("ID parameter is missing");
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Member ID is required");
        }
		this.getServletContext().getRequestDispatcher("/views/team/edit.jsp").forward(req, res);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Long id =  Long.parseLong(req.getParameter("id"));
                Boolean deleted = teamRepository.deleteTeam(id);
                TeamModel model = new TeamModel();

                if(deleted){
                    model.setSuccess("team delted successfully");
                    req.setAttribute("model", model);
                    res.sendRedirect("team?action=list");
                }else{
                    // RequestDispatcher dispatcher = req.getRequestDispatcher("errorPage.jsp");
                    // dispatcher.forward(req, res);
                    return;
                }
        } catch (Exception e) {
            logger.error("Error ", e);
        }
    }

    // protected void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    // {
     
    // }
}
