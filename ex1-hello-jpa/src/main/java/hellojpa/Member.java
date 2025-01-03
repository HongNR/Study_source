package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity // JPA가 로딩될 때 JPA 사용하는 것으로 인식하고 관리함
//@Table(name = "USER")   // 엔티티와 매핑할 테이블 지정(=USER라는 테이블 다루기)
/*@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",    // 매핑할 데이터베이스 시퀀스 이름
        initailValue = 1, allocationSize = 50)*/  // allocationSize: 시퀀스 한번 호출에 증가하는 수(성능 최적화에 사용)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*
    * Generation.IDENTITY : 기본 키 생성을 데이터베이스에 위임
    * Generation.SEQUENCE :
    * Generation.TABLE : 키 생성 전용 테이블을 하나 만들어 db 시퀀스를 흉내내는 전략
    *  */
    @Column(name = "MEMBER_ID")
    private Long id;

    /*
        @Column
        nullable = false : not null
        unique = true : unique 제약조건이나 이름이 랜덤이기 때문에 안 씀
        columnDefinition = "varchar(100) ..." : 컬럼 정보 직접 주기
    */
    @Column (name = "USERNAME")/*(length = 10)*/   // (name = "username")컬럼명 지정
    private String username;
    /*
    private Integer age;
    @Enumerated(EnumType.STRING)    // : enum 이름을 데이터베이스에 저장
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)   // : LocalDate, LocalDateTime 사용 시에는 생략 가능
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;
    @Transient  // 메모리에서만 사용하고 db에서는 사용 안할 때
    private int temp;
    */

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",
        joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY)                      // 관계(Many쪽이 무조건 주인)
    @JoinColumn(name = "TEMA_ID")   // join하는 pk
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);    // 매핑시 양쪽에 값을 설정하기 위해 작성
    }

    public Member() {
        // default constructor
    }

    //기간 Period
    @Embedded
    private Period workPeriod;




}
