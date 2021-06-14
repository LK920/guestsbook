package org.zerock.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    //DTO리스트
    private List<DTO> dtoList;

    private int totalPage; //총 페이지 번호
    private int page; //현재 페이지 번호
    private int size; // 목록 사이즈
    private int start, end; //시작페이지 번호, 끝 페이지 번호
    private boolean prev, next; //이전, 다음

    // 페이지 번호 목록
    private List<Integer> pageList;
    /*
        Page<>타입을 생성자로 생성
        function<EN,DTO>는 엔티티 객체들을 dto로 변환해주는 기능
     */
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        /*dtolist에 변환된 dto를 list<>타입으로 저장*/
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() +1; //0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        start = tempEnd-9;
        prev = start>1;
        end = totalPage>tempEnd ? tempEnd:totalPage;
        next = totalPage>tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
