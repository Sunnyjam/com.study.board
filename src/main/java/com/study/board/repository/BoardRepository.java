package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //<어떤entity를 넣지, primary key로 지정한 type>
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
