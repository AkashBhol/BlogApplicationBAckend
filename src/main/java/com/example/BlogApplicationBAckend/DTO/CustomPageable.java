package com.example.BlogApplicationBAckend.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class CustomPageable {

    private int pageNumber = 0;

    private int pageSize = 10;
}
