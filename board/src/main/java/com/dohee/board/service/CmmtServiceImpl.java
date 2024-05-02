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
        return result;
    }
    
}
