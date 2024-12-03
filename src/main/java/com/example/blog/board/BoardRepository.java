package com.example.blog.board;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public Optional<Board> findByIdJoinUserAndReply(int id) {
        String sql = """
                select b from Board b join fetch b.user left join fetch b.replies r left join fetch  r.user where b.id = :id
                """;
        Query  q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);
        try {
            Board board = (Board) q.getSingleResult();
            return Optional.of(board);
        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

    public Optional<Board> findByIdJoinUser(int id) {
        String sql = """
                select b from Board b join fetch b.user where b.id = :id
                """;
        Query  q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);
        try {
            Board board = (Board) q.getSingleResult();
            return Optional.of(board);
        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }
    }

    public void delete(int id) {
        em.createQuery("delete from Board b where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void save(Board board) {
        // 비영속
        em.persist(board);
        // 동기화 완료 (영속화됨)
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }

    public Optional<Board> findById(int id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }
}








