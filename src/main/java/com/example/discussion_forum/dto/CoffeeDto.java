package com.example.discussion_forum.dto;


import com.example.discussion_forum.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoffeeDto {
    private Long id;
    private String name;
    private int price;

   public Coffee toEntity(){
       return new Coffee(id, name, price);
   }

}
