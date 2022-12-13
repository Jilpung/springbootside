package com.boardsideproject.springbootside.dto;


import com.boardsideproject.springbootside.domain.Posts;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostsDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        
        /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
        private Long id;
        private String title;
        private String writer;
        private String content;
        private String createdDate, modifiedDate;
//        private int view;
//        private User user;

        /** Dto -> Entity **/
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
//                    .view(0)
//                    .user(user)
                    .build();

            return posts;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {

        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final String createdDate, modifiedDate;
//        private final int view;
//        private final Long userId;
//        private final List<CommentDto.Response> comments;

        /** Entity -> Dto **/
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
//            this.view = posts.getView();
//            this.userId = posts.getUser().getId();
//            this.comments = posts.getComment().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}