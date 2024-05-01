package com.dohee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dohee.board.dto.Board;
import com.dohee.board.dto.Option;
import com.dohee.board.dto.Page;



@Mapper     // MyBatis의 매퍼 인터페이스로 지정하는 어노테이션
public interface BoardMapper {

    // 게시글 목록 - [페이징] + [검색]
    //public List<Board> list(Page page) throws Exception;
    public List<Board> list(@Param("page") Page page, @Param("option") Option option) throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 데이터 개수 조회
    public int count(@Param("option") Option option) throws Exception;

    // 게시글 목록 - [검색]
    // 객체로 넘겨주는게 아니면 MyBatis에서는 @Param 어노테이션 붙여주면 좋음.(매칭되는게 하나뿐일땐 무조건 매칭됨)
    public List<Board> search(@Param("option") Option option) throws Exception;

    // 조회수 증가
    public int updateViews(Board board) throws Exception;
    
}