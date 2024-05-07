-- Active: 1714007450769@@127.0.0.1@3306@spring
-- 댓글
-- * 게시글(board)에 종속된 테이블

CREATE TABLE comment (
    c_no         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,   -- 댓글번호
    board_no        INT NOT NULL,                        -- 종속되는 글번호
    parent_no       INT NOT NULL,                       -- 부모댓글 번호
    writer          VARCHAR(100) NOT NULL,              -- 작성자
    content         TEXT NOT NULL,                      -- 내용
    reg_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- 등록일자
    upd_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP    -- 수정일자
);

-- 댓글 샘플데이터
-- 글번호: 249
INSERT INTO comment(board_no, parent_no, writer, content)
VALUES (249, 0, '김조은', '야야옹')
      ,(249, 0, '김조은', '야야야옹')
      ,(249, 0, '김조은', '야야야야옹')
      ,(249, 0, '김조은', '야야야야야옹')
      ,(249, 0, '김조은', '야야야야야야옹')
      ,(249, 0, '김조은', '냐옹')
;

SELECT *
FROM comment;

-- 댓글 (comment) 테이블에 외래키 추가
ALTER TABLE comment
ADD CONSTRAINT FK_CMMT_BOARD_NO
Foreign Key (board_no) REFERENCES board(no)
ON DELETE CASCADE;

-- FOREIGN KEY (외래키 적용할 속성) REFERENCES 외래키로 가져올 테이블 명(외래키로 가져올 속성 명)


-- 테이블 생성시, 외래키 지정하는 ㅓㅂ
CREATE TABLE comment (
    c_no         INT NOT NULL AUTO_INCREMENT PRIMARY KEY  -- 댓글번호
    board_no        INT NOT NULL,                        -- 종속되는 글번호
    parent_no       INT NOT NULL,                       -- 부모댓글 번호
    writer          VARCHAR(100) NOT NULL,              -- 작성자
    content         TEXT NOT NULL,                      -- 내용
    reg_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- 등록일자
    upd_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP    -- 수정일자
    -- 외래키 지정
    Foreign Key (board_no) REFERENCES board(no) ON DELETE CASCADE -- 종속 삭제
                                                ON UPDATE CASCADE -- 종속 업데이트 
);

-- CASCADE: 연쇄 삭제 가능
-- RESTRICT : 자식이 살아있을 경우 부모 삭제 불가



-- 251번 게시글
SELECT *
FROM board
WHERE no = 251;
-- 251번 댓글
SELECT *
FROM comment
WHERE board_no = 251;
