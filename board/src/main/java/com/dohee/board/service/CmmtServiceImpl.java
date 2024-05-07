package com.dohee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dohee.board.dto.Comment;
import com.dohee.board.mapper.CommentMapper;

@Service
public class CmmtServiceImpl implements CmmtService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> cmmtList(int boardNo) throws Exception {

        List<Comment> cmmtList = commentMapper.cmmtList(boardNo);

        return cmmtList;
    }

    @Override
    // select는 CRUD 연습용~ 쓸모는 없음.. 아마?
    public Comment select(int no) throws Exception {
            Comment cmmt = commentMapper.select(no);

            return cmmt;
    }

    @Override
    public int insert(Comment comment) throws Exception {

        int result = commentMapper.insert(comment);
        
        // 일반 댓글 등록
        // 댓글 번호와 부모 번호를 똑같이 수정
        if(result > 0 && comment.getParentNo() == 0) {
            // 등록한 댓글의 부모번호에 댓글 최댓값(방금 댓글 insert햇으니 댓글번호는 최댓값)을 설정하여 수정요청 
            int no = commentMapper.max();
            // 매개변수로 넘어온 cmmt 객체는 insert 후 AI로 번호가 지정되니
            // 추후에 번호랑 부모번호 지정하여 update 요청
            comment.setCNo(no); 
            comment.setParentNo(no);
            commentMapper.update(comment);
            System.out.println("부모번호: " + comment.getParentNo());
        }
        
        // 답글등록인 경우
        // 부모 번호가 지정되서 등록 (댓글번호 != 부모 번호)

        return result;

    }

    @Override
    public int update(Comment comment) throws Exception {
        int result = commentMapper.update(comment);

        System.out.println("결과: " + result);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = commentMapper.delete(no);

        // 대댓글 삭제
        if (result > 0) {
            result += deleteByParentNo(no);
        }
        return result;
    }

    // 댓글 종속 삭제 - 게시글 삭제와 연결(BoardServiceImpl.java)
    @Override
    public int deleteByBoardNo(int boardNo) throws Exception{
        int result = commentMapper.deleteByBoardNo(boardNo);
        return result;
    }

    // 댓글번호 최댓값 가져오기
    @Override
    public int max() throws Exception {
        int max = commentMapper.max();

        return max;
    }

    // 대댓글 종속 삭제 - 댓글 삭제와 연결
    @Override
    public int deleteByParentNo(int parentNo) throws Exception {
        int result = commentMapper.deleteByParentNo(parentNo);

        return result;
    }
    
}
