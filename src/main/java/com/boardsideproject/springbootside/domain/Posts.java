package com.boardsideproject.springbootside.domain;


import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    @OrderBy("id asc")
//    private List<Comment> comments;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
