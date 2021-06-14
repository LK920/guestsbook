package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;
    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            Guestbook guestbook = Guestbook.builder().title("Title........"+i)
                    .content("Content....."+i)
                    .writer("user"+(i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }
    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        //존재하는 번호로 테스트
        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeTitle("ChangeTitle......");
            guestbook.changeContent("ChangeContent......");

            guestbookRepository.save(guestbook);
        }
    }
    //단일항목 검색 테스트
    @Test
    public void testQuery1(){
        //페이지 구하기 0부터 10개씩 묶음으로 gno를 기준으로 내림차순 정렬
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
        //querydsl을 이용하기 위해서 QGuestbook에 있는 guestbook을 가져옴
        QGuestbook qGuestbook = QGuestbook.guestbook; //1
        String keyword = "1";
        //where문에 들어갈 조건들을 넣어주는 컨터이너 역할
        BooleanBuilder builder = new BooleanBuilder();//2
        //where문에 들어갈 조건을 설정
        BooleanExpression expression = qGuestbook.title.contains(keyword);//3
        //컨테이너에 조건 결합
        builder.and(expression);//4
        //guestbookRepository에서 findall()사용하여 조건에 맞는 전체 결과를 페이지로 묶고정렬
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);//5
        //앞선 결과값은 출력
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });

    }
    //다중항목 검색 테스트
    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        //다중 조건 설정
        BooleanExpression exTitle = qGuestbook.title.contains(keyword);//조건1
        BooleanExpression exContent = qGuestbook.content.contains(keyword);//조건2
        BooleanExpression exAll = exTitle.or(exContent);//조건1+조건2
        //컨테이너에 조건 결합
        builder.and(exAll);
        //컨테이너에 0보다 큰 gno 숫자를 검색하는 조건 결합
        builder.and(qGuestbook.gno.gt(0L));//?
        //결과 검색 및 페이징처리
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        //결과값 출력
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });


    }
}
