package com.cleanCar.freeTicket.utils;


import com.cleanCar.freeTicket.utils.enums.DelYn;
import com.cleanCar.freeTicket.utils.enums.UseYn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@MappedSuperclass
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", insertable = false, columnDefinition = "VARCHAR(1) DEFAULT 'Y'")
    private UseYn useYn;

    @Enumerated(EnumType.STRING)
    @Column(name = "del_yn", insertable = false, columnDefinition = "VARCHAR(1) DEFAULT 'N'")
    private DelYn delYn;

    @CreatedDate
    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private LocalDateTime updatedDt;

}
