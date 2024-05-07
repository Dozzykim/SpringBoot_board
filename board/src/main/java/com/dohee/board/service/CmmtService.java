package com.dohee.board.service;

import java.util.List;

import com.dohee.board.dto.Comment;

public interface CmmtService {

    // 종속된 댓글 조회
    public List<Comment> cmmtList(int boardNo) throws Exception;

    // select 연습용~ 쓸모는 없음.. 아마?
    public Comment select(int no) throws Exception;

    // 댓글 등록
    public int insert(Comment comment) throws Exception;
    
    // 댓글 수정
    public int update(Comment comment) throws Exception;
    
    // 댓글 삭제
    public int delete(int no) throws Exception;

    // 댓글 종속 삭제 - 게시글 삭제와 연결(BoardServiceImpl.java)
    public int deleteByBoardNo(int boardNo) throws Exception;

    // 댓글번호 최댓값 가져오기
    public int max() throws Exception;

    // 대댓글 종속 삭제 - 댓글 삭제와 연결
    public int deleteByParentNo(int parentNo) throws Exception;


}
