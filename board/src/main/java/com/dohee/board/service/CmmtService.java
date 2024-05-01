package com.dohee.board.service;

import java.util.List;

import com.dohee.board.dto.Board;
import com.dohee.board.dto.Comment;

public interface CmmtService {

    // 종속된 댓글 조회
    public List<Comment> cmmtList(Board board) throws Exception;

    // 댓글 등록
    public int insert(Comment comment) throws Exception;
    
    // 댓글 수정
    public int update(Comment comment) throws Exception;
    
    // 댓글 삭제
    public int delete(Comment comment) throws Exception;


}
