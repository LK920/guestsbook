package org.zerock.guestbook.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//MappedSuperclass는 테이블로 생성되지 않고 해당 클래스를 상속한 엔티티의 클래스로 db테이블 생성
//listensers는 엔티티의 변화를 감지한다
//AuditingEntityListener은 엔티티 객체가 생성/변경되는 것을 감지하는 역할
//-> 이를 활성화 시키기 위해선 프로젝트에 enableJpaAuditing 추가해야함
@MappedSuperclass
@EntityListeners(value ={AuditingEntityListener.class})
@Getter
abstract class BaseEntity {
    //데이터의 등록 수정 시간 자동처리를 위함 엔터티
    //CreateDate는 jpa에서 엔티티의 생성시간처리
    //LastModifiedDate는 최종 수정시간 처리 - 속성으로 insertable과 updatable이 있음
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
