package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;

@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 다대일 관계에서는 '다'쪽이 관계의 주인
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    // @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    // Period
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    @Embedded
    private Period period;

    // Address
//    private String city;
//    private String street;
//    private String zipcode;
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
            @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    /*
    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns =
            @JoinColumn(name = "MEMBER_ID") // 외래키 매핑
    )
    private List<Address> addressHistory = new ArrayList<>();
    */
    // -> 값 타입으로 사용하는 것보다 연관관계 매핑을 이용해라
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // 연관관계 편의 메소드
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }

    // 이렇게 서로 toString을 호출하게되면 무한 루프가 발생한다.
    /*
    @Override
    public String toString() {
        return "Member{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", team=" + team.toString() +
            '}';
    }
    */

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
