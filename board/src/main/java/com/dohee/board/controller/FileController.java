package com.dohee.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dohee.board.dto.Files;
import com.dohee.board.service.FileService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private FileService fileService;

    @Value("${upload.path}")    // application.properties에 설정한 업로드 경로
    private String uploadPath;

    /**
     * 파일 다운로드
     * @param no
     * @param response
     * @throws Exception
     */
    @GetMapping("/{no}")
    public void getMethodName(@PathVariable("no") int no
                                ,HttpServletResponse response) throws Exception {
                                    
        // DB 에서 파일경로를 읽어오기 위해 파일 객체로 찾아오기
        Files downloadFile = fileService.download(no);

        // 다운로드할 파일이 없는 경우.
        if (downloadFile == null) {
            // download 할 파일이 없으면, 그냥 메시지만 출력
            return;
        }
        
        // 다운로드할 파일이 있는 경우,
        // DB에서 찾은 파일 객체를 가져와서 파일명과 경로를 꺼내기. (FileInputSteam에서 써야함)
        String fileName = downloadFile.getFileName();   // 파일 명
        String filePath = downloadFile.getFilePath();   // 파일 경로

        // 다운로드를 위한 응답 헤더 세팅
        // - ContentType        : application/octet-stream
        // - Content-Disposition: attachment, filename="파일명.확장자"
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        // 한글깨짐 방지
        fileName = URLEncoder.encode(fileName, "UTF-8");

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        // 파일 다운로드 (요 File은 내가 만든 객체 X, 자바에서 지원하는 File임 찐파일!!!)
        // 경로를 생성자에 넣어서, 그 경로에 있는 파일을 넣음
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);        // 파일 입력
        ServletOutputStream sos = response.getOutputStream();   // 파일 출력
        FileCopyUtils.copy(fis, sos);

        fis.close();
        sos.close();
    }

    /**
     * 파일 삭제
     * @param no
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteFile( @PathVariable("no") int no ) throws Exception {
        log.info("[DELETE] - /file/" + no);

        int result = fileService.delete(no);

        // 삭제 성공
        if (result>0) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }

        // 삭제 실패
        return new ResponseEntity<>("FAIL", HttpStatus.OK);

    }

    /**
     * 이미지 썸네일
     * @param no
     * @return
     * @throws Exception 
     */
    // /file/img/{no}
    @GetMapping("/img/{no}")
    public ResponseEntity<byte[]> thumnailImg(@PathVariable("no") int no) throws Exception {

        // 파일 번호로 파일 정보 조회
        Files file = fileService.select(no);  // 매개변수로 받은 번호에 해당하는 파일 하나 가지고 옴.

        // Null 체크
        if (file == null) {
            String filePath = uploadPath + "/no-img.jpg";
            File noImageFile = new File(filePath);
            byte[] noImageFileData = FileCopyUtils.copyToByteArray(noImageFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(noImageFileData, headers, HttpStatus.OK);
        }

        // 파일 정보 중에서 파일 경로 가져오기
        String filePath = file.getFilePath();

        // 실제 파일 객체 생성 (자바에 있는 파일 입출력 객체) -> 파일 경로를 넣어주면 해당 파일이 대입됨.
        File f = new File(filePath);

        // 파일 데이터 -> 바이트 데이터를 가져와 줌
        byte[] fileData = FileCopyUtils.copyToByteArray(f);

        
        // 이미지 컨텐츠 타입 지정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        // ResponseEntity<>(데이터, 헤더, 상태코드)
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    
    // 타임리프를 사용하면 String으로 반환했을 때, 스프링 리졸버랑 상호작용하여 뷰페이지를 뱉음
}
