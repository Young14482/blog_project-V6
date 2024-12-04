package com.example.blog.reply;

import com.example.blog.board.Board;
import com.example.blog.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "reply_tb")
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String comment;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, Board board, User user, String comment, Timestamp createdAt) {
        this.id = id;
        this.board = board;
        this.user = user;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
