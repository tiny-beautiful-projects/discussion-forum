package com.example.discussion_forum.dto;


import com.example.discussion_forum.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


// 데이터 전달 및 변환 (DTO의 데이터를 Entity로 바꾸거나, Entity의 데이터를 DTO로 바꾸는 작업을 담당)
@NoArgsConstructor
@AllArgsConstructor
@ToString
// 클래스의 필드 값을 가져오는 Getter 메서드를 자동으로 만들어준다.
@Getter
public class PizzaDto {
    private Long id;
    private String name;
    private Integer price;

    // Entity => Dto
    public static PizzaDto toDto(Pizza pizza) {
     return new PizzaDto(
             pizza.getId(),
             pizza.getName(),
             pizza.getPrice()
     );
    }

    // DTO => entity
    public Pizza toEntity() {
        return new Pizza(null, name, price);
    }
}
