package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor//의존성 자동주입
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;//반드시 final 선언

    @Override
    public Long register(GuestbookDTO dto){

        log.info("DTO-------------------");
        log.info(dto);
        //인터페이스에서 내용가진 코드를 받아옴
        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }
    /*
        inferface에서 정의된 entityToDto 객체를 getList 메서드를 이용해서 화면처리에 필요한 값들을 구현
    */
    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO){
        /*gno로 내림차수 정렬*/
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity-> entityToDto(entity));

        return new PageResultDTO<>(result,fn);
    }
}
