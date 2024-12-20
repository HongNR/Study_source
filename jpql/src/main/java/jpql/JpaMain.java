package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   // connection을 받은 행위

        EntityTransaction tx = em.getTransaction(); // 모든 데이터변경은 트랜잭션을 통해 이뤄짐
        tx.begin(); // db시작

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // 페이징 API 예시
            /*
            em.createQuery("select m from Member m order by m.age desc", Member.class)
                .setFirstResult(10)
                .setMaxResults(10)
                .getResultList();
                    */

            tx.commit();    // 커밋 필수 & 커밋하는 순간 sql을 데이터에 보냄
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();     // 자원 꼭 닫아주기
        }

        emf.close();
    }

}
