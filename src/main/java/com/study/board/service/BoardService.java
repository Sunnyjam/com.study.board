package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

   //글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{
        //1.지정할 경로를 지정
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //2. 파일에 붙일 랜덤 이름을 생성 UUID=식별자, UUID.randomUUID()=랜덤으로 이름을 만들어줌
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        //3. 파일을 생성할건데, 경로(projectPath)를 넣고 파일명(fileName)을 넣어준다. File class를 이용해서 생성, 경로 지정
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        //4.Board에 담길 파일(setFilename), 저장될 경로(setFilepath)을 넣준다,
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        //static 하위는 아래 경로만 접근가능하기 때문에 \\src\\main\\resources\\static\\files가 아닌 /files/로 작성한다.

        boardRepository.save(board);
    }
    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);

        //return값을 List<>에서 Page<>로 변경
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);

    }

    //특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
