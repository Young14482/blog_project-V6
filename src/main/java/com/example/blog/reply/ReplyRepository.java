package com.example.blog.reply;

import com.example.blog._core.error.ex.Exception401;
import com.example.blog.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;
    public void deleteAll(int boardId){
        Query q = em.createQuery("delete from Reply r where r.board.id=:boardId");
        q.setParameter("boardId", boardId);
        q.executeUpdate();
    }
    public void updateNull(int boardId){
        Query q = em.createQuery("update Reply r set r.board.id=null where r.board.id=:boardId");
        q.setParameter("boardId", boardId);
        q.executeUpdate();
    }
}