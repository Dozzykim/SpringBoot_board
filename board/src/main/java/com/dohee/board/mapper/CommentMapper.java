package com.dohee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dohee.board.dto.Board;
import com.dohee.board.dto.Comment;

@Mapper
public interface CommentMapper {

    // 종속된 댓글 조회
    public List<Comment> cmmtList(Board board) throws Exception;

    // select 연습용~ 쓸모는 없음.. 아마?
    public Comment select(int no) throws Exception;

    // 댓글 등록
    public int insert(Comment comment) throws Exception;
    
    // 댓글 수정
    public int update(Comment comment) throws Exception;
    
    // 댓글 삭제
    public int delete(Comment comment) throws Exception;
} 

    
    

