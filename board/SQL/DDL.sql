-- Active: 1714007450769@@127.0.0.1@3306@spring

CREATE TABLE `board` (
  `no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`)
) COMMENT='게시판';


CREATE TABLE `file` (
  `no` int NOT NULL AUTO_INCREMENT,
  `parent_table` varchar(45) NOT NULL,
  `parent_no` int NOT NULL,
  `file_name` text NOT NULL,
  `origin_name` text,
  `file_path` text NOT NULL,
  `file_size` int NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_code` int NOT NULL DEFAULT '0',         # 파일종류 코드 => 1:썸네일, 2: 일반첨부파일...
  PRIMARY KEY (`no`)
) COMMENT='파일';

-- 테이블 데이터 전체 삭제 
TRUNCATE board;
TRUNCATE file;


-- 테이블 조인 조회
SELECT b.*, file_no
FROM board b LEFT JOIN (
                        SELECT parent_no, no as file_no, file_name, file_code, file_path
                        FROM file
                        WHERE parent_table="board" AND file_code = 1
                        )f
ON (b.no = f.parent_no);       # file_code = 1 (대표 썸네일)


SELECT *
FROM file
;