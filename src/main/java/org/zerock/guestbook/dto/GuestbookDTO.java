package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//builder어노테이션 getter,setter를 대체
@Builder
//NoArgsConstructor은 파라미터값이 없는 기본 생성자 생성가능
@NoArgsConstructor
//AllArgsConstructor은 모든 필드값을 파라미터로 받는 생성자 생성
@AllArgsConstructor
//@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정해주는 매우 유용한 어노테이션
@Data
public class GuestbookDTO {

    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
}
