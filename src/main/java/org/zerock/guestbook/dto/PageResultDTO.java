package org.zerock.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> {

    private List<DTO> dtoList;
    /*
        Page<>타입을 생성자로 생성
        function<EN,DTO>는 엔티티 객체들을 dto로 변환해주는 기능
     */
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        /*dtolist에 변환된 dto를 list<>타입으로 저장*/
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
