package repository;
import java.util.Optional;
import java.sql.SQLException;
import java.util.List;
import entities.Member;


public interface IMemberRepository {
    
    public boolean create(Member member)throws SQLException ;
    public Optional <Member> findMemberById(Long id)throws SQLException ;
    public List<Member> getAllMembers() throws SQLException;
    public Member updateMember(Member member) throws SQLException;
    public Boolean deleteMember(Long id) throws SQLException;


    
    

}
