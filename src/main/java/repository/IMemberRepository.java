package repository;
import java.util.Optional;
import java.util.List;
import entities.Member;


public interface IMemberRepository {
    
    public boolean create(Member member);
    public Optional <Member> findMemberById(Long id);
    // public List<Member> getAllMembers();
    

}
