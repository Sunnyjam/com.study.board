package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {

    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto, identity=mysql, sequence=oracle
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;
}
