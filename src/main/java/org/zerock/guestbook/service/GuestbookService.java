package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);
    /*getList 객체 선언*/
    PageResultDTO<GuestbookDTO,Guestbook> getList(PageRequestDTO requestDTO);
    
    //default : 인터페이스 내에서 추상클래스를 통해서 전달해야하는 실제 코드를 선언할 수 있게 함
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    /*
        PageRequestDTO를 파라미터로, PageResultDTO를 리텁타입으로 사용하는 getList() 설계
        엔티티객체를 dto객체로 변환하는 entityToDto()를 정의
     */
    

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return  dto;
    }
}
