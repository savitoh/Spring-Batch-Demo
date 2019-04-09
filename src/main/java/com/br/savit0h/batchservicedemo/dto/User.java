package com.br.savit0h.batchservicedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    private Long id;

    private String name;

    private String job;

    private LocalDateTime createdAt;

}
