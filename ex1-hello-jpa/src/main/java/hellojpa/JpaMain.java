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

            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("동일성 비교 : "+ (findMember1 == findMember2)); // true

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

            tx.commit();    // 커밋 필수 & 커밋하는 순간 sql을 데이터에 보냄
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();     // 자원 꼭 닫아주기
        }

        emf.close();
    }
}
