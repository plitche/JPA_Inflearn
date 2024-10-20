package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private long id;
    private String name;

    // 반대편에 있는 어떤 변수명과 매핑이 되어있는지
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

    // 이렇게 서로 toString을 호출하게되면 무한 루프가 발생한다.
    /*
    @Override
    public String toString() {
        return "Team{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", members=" + members +
            '}';
    }
    */
}
