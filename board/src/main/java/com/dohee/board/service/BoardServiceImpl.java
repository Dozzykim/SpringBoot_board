package com.dohee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dohee.board.dto.Board;
import com.dohee.board.dto.Files;
import com.dohee.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service    // 서비스 역할의 스프링 빈 등록
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

    /**
     * 게시글 전체 조회
     */
    @Override
    public List<Board> list() throws Exception {

        List<Board> boardList = boardMapper.list();

        return boardList;
    }

    /**
     * 게시글 조회
     * - no 매개변수로 게시글 번호를 전달받아서
     *      데이터베이스에 조회 요청
     */
    @Override
    public Board select(int no) throws Exception {

        Board board = boardMapper.select(no);

        return board;
    }

    /**
     * 게시글 등록
     * Board 객체를 매개변수로 전달받아서
     * 데이터베이스에 등록 요청
     */
    @Override
    public int insert(Board board) throws Exception {

        // 게시글 등록 처리
        int result = boardMapper.insert(board);
        String parentTable = "board";
        int parentNo = boardMapper.maxPk(); // 방금 등록한 게시글 번호를 가져옴

        // 썸네일 업로드
        // - 부모테이블, 부모번호, 멀티파트파일, 파일코드:1(썸네일)
        MultipartFile thumbnailFile = board.getThumbnail();

        //썸네일 파일 첨부 했는지 먼저 체크
        if ( thumbnailFile != null && !thumbnailFile.isEmpty()) {
            
            Files thumbnail = new Files();
            
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(1);   // 썸네일 파일코드(1)
            fileService.upload(thumbnail);      // 썸네일 파일 업로드
        }

        // 첨부파일 업로드
        // 게시글에 첨부한 파일들을 List로 받아옴
        List<MultipartFile> fileList = board.getFile();

        if (!fileList.isEmpty()) { // 첨부파일이 있나 없나 체크
            for (MultipartFile file : fileList) { // 첨부파일 여러개일 수 있으니 foreach
                if (file.isEmpty()) {
                    continue;
                }
                
                // fileService에 매개변수로 넘길 file 객체 세팅
                Files uploadFile = new Files();
                //uploadFile.setFileCode(?????????????????????);
                uploadFile.setParentTable(parentTable); // 게시판정보String
                uploadFile.setParentNo(parentNo); // 종속될 게시글 번호
                uploadFile.setFile(file); // 포이치로 가져오는 파일(객체x 찐파일) 넣기

                // 파일 업로드 요청
                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    /**
     * 게시글 수정
     * Board 객체를 매개변수로 전달받아서
     * 데이터베이스에 수정요청
     */
    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);

        return result;
    }

    /**
     * 게시글 삭제
     * no 매개변수로 게시글 번호를 전달받아
     * 데이터베이스에 삭제요청
     */
    @Override
    public int delete(int no) throws Exception {
        int result = boardMapper.delete(no);

        return result;
    }


}

