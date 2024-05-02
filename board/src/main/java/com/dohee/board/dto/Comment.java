package com.dohee.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

    private int cNo;       // 댓글번호
    private int boardNo;   // 종속되는 글번호
    private int parentNo;  // 부모댓글 번호
    private String writer;  // 작성자
    private String content; // 내용
    private Date regDate;   // 등록일자
    private Date updDate;   // 수정일자
    
    
}
