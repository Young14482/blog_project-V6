package com.example.blog.board;

import com.example.blog._core.util.MyDate;
import com.example.blog.reply.Reply;
import com.example.blog.user.User;
import lombok.Data;

import java.util.List;
import java.util.Objects;

public class BoardResponse {

    @Data
    public static class UpdateFormDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public UpdateFormDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());
        }
    }

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        private Integer userId;
        private String username;
        private boolean isOwner = false;

        private List<ReplyDTO> replies;

        @Data
        class ReplyDTO {
            private int id;
            private String comment;
            private int userId;
            private String username;
            private boolean isOwner = false;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                if(sessionUser != null) {
                    this.isOwner = sessionUser.getId() == reply.getUser().getId();
                }
            }
        }

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdAt = MyDate.formatToStr(board.getCreatedAt());

            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // Lazy Loading

            if (sessionUser != null) {
                this.isOwner = Objects.equals(board.getUser().getId(), sessionUser.getId());
            }
            this.replies = board.getReplies().stream().map(r -> new ReplyDTO(r, sessionUser)).toList();
        }
    }

    @Data
    public static class DTO {
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
