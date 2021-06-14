package org.zerock.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QuerydslPredicateExecutor<Guestbook> {
    //JpaRepository를 상속하는데 이때 <"사용할 엔티티","엔티티내의 설정된 ID 타입">으로 설정해줘야한다
    //QuerydslPredicateExecutor<>는 querydsl를 이용하기 위해 추가로 상속한 것
}
