package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        /*
            엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
            엔티티 매니저는 쓰레드간에 공유 X(사용하고 버려야 한다.)
        */
        // persistence.xml에 설정해놓은 정보를 토대로 실행됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   // connection을 받은 행위

        EntityTransaction tx = em.getTransaction(); // 모든 데이터변경은 트랜잭션을 통해 이뤄짐
        tx.begin(); // db시작

        try {

            /*
            //비영속(객체를 생성한 상태)
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속(객체를 저장한 상태)
            em.persist(member);     // -> 1차캐시에 저장
            */

            /*
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("동일성 비교 : "+ (findMember1 == findMember2)); // true
            */

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA"); // name 수정

            /*
                JPQL : 엔티티 객체를 대상으로 쿼리(객체 지향 쿼리)
                SQL : 데이터베이스 테이블을 대상으로 쿼리
            */
            /*List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setMaxResults(5)
                    .setMaxResults(8)   // 페이지네이션
                    .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }*/

            /*Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");    // 데이터 수정 , em.persist는 작성하지 않음*/

            /*Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();     // 커밋 전 강제로 반영 = 직접 호출,,,,, JPQL 쿼리 실행 때는 자동으로 플러시 호출*/
            /* 플러시란
            * 영속성 컨텍스트를 비우지 않음
            * 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
            * 트랜잭션이라는 작업단위가 중요 -> 커밋 직전에만 동기화하면 됨
            */

            /*Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            // 준영속 (=JPA가 관리 안 하겠다)
            em.detach(member);  // 특정 엔티티 : 사실상 직접 쓸일은 없음
            em.clear();         // 영속성 컨텍스트를 통으로 다 초기화
            em.close();         // 영속성 종료*/

            System.out.println("=========");

            /*  양방향 매핑 정리
                단방향 매핑만으로도 이미 연관관계 매핑은 완료
                양방향 매핑은 반대 방향으로 조회기능이 추가된 것 뿐
                JPQL에서 역방향으로 탐색할 일이 많음
                단방향 매핑을 잘 하고 양방향은 필요할 때 추가해도 됨
            ** 연관관계의 주인은 외래 키의 위치를 기준으로 정해야함!
            */

            /*Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie : "+ findMovie);*/

            Member member = em.find(Member.class, 1L);
            printMember(member);
//            emf.getPersistenceUnitUtil().isLoaded(member);

//            printMemberAndTeam(member);


            /* entity 대상으로 검색 */
            em.createQuery(
                    "select m from Member m where m.username like '%kim'"
                    , Member.class
            ).getResultList();

            tx.commit();    // 커밋 필수 & 커밋하는 순간 sql을 데이터에 보냄
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();     // 자원 꼭 닫아주기
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username: " + username);

        Team team = member.getTeam();
        System.out.println("team: " + team);
    }
}
