package com.dohee.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dohee.board.dto.Comment;
import com.dohee.board.service.CmmtService;

import lombok.extern.slf4j.Slf4j;


/**
 * 댓글 목록 : 🔗[GET] /cmmt/{boardNo}
 * 댓글 등록 : 🔗[POST] /cmmt
 * 댓글 수정 : 🔗[PUT] /cmmt
 * 댓글 삭제 : 🔗[DELETE] /cmmt
 */
@Slf4j
@Controller
@RequestMapping("/cmmt")
public class CommentController {
    
    @Autowired
    private CmmtService cmmtService;

    /**
     * 댓글 조회
     * @param boardNo
     * @return
     * @throws Exception
     */
    // 1. JSON 데이터 응답 후, 클라이언트 측에서 렌더링(CSR; 클라이언트 사이드 렌더링)
    // @GetMapping("/{boardNo}")
    // public ResponseEntity< List<Comment> > list(@PathVariable("boardNo") int boardNo) throws Exception {
    //     // 게시글번호에 따른 댓글 목록 요청
    //     List<Comment> cmmtList = cmmtService.cmmtList(boardNo);
        
    //     return new ResponseEntity<>(cmmtList, HttpStatus.OK);
    // }

    // 2. 서버 측에서 렌더링 후 HTML(뷰) 응답
    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") int boardNo, Model model) throws Exception {
        // 게시글 번호에 따른 댓글 목록 요청
        List<Comment> cmmtList = cmmtService.cmmtList(boardNo);
        
        // 모델에 등록
        model.addAttribute("cmmtList", cmmtList);

        // 뷰 페이지 지정
        return "comment/list";
    }

    /**
     * 댓글 등록
     * @param comment
     * @return
     * @throws Exception
    */
    @PostMapping("")
    public ResponseEntity<String> insert(@RequestBody Comment comment) throws Exception {
        log.info("comment: " + comment);
        // 데이터 요청
        int result = cmmtService.insert(comment);

        if (result > 0) {
            // 데이터 처리 성공
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED); //CREATED = 201
        }

        // 데이터 처리 실패
        return new ResponseEntity<>("FAIL", HttpStatus.OK); // OK = 200
    }

    /**
     * 댓글 수정
     * @param comment
     * @return
     * @throws Exception
    */
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Comment comment) throws Exception {
        // 데이터 요청
        log.info("comment: " + comment);

        int result = cmmtService.update(comment);

        if (result > 0) {
            // 데이터 처리 성공
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK); // OK = 200
        }

        // 데이터 처리 실패
        return new ResponseEntity<>("FAIL", HttpStatus.OK); // OK = 200
    }

    /**
     * 댓글 삭제
     * @param no
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{no}")
    public ResponseEntity<String> delete(@PathVariable("no") int no) throws Exception {
        int result = cmmtService.delete(no);

        if (result > 0) {
            // 데이터 처리 성공
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }

        // 데이터 처리 실패
        return new ResponseEntity<>("FAIL", HttpStatus.OK); // OK = 200

    }
    
    


}
