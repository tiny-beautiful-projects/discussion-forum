package com.example.discussion_forum.dto;


import com.example.discussion_forum.entity.Coffee;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeDto {
    private Long id;
    private String name;
    private Integer price;

   public Coffee toEntity(){
       return new Coffee(id, name, price);
   }

}
