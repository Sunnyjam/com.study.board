package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //<어떤entity를 넣지, primary key로 지정한 type>
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
//게시물리스트를 리스트가 아니 페이지로 보여줄 거기 때문에 List<>가 아닌 Page<>를 사용한다.