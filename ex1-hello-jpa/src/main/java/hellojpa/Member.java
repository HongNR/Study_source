package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // JPA가 로딩될 때 JPA 사용하는 것으로 인식하고 관리함
// @Table(name = "USER")   // USER라는 테이블 다루기
public class Member {

    @Id
    private Long id;

    // @Column(name = "username")   // 컬럼명 지정
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
