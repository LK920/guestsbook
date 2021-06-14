package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
/*
    화면에 전달되는 page와 size 파라미터를 수집하는 용도
    기본값을 갖는게 좋기에 1과 10으로 설정
    해당 DTO의 진짜 목적은 jpa 에서 사용하는 pageable타입의 객체를 생성하는 것
    sort는 추후 정렬을 사용하기 위해서 설계
 */
    private int page;
    private int size;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1, size,sort);
    }
}
