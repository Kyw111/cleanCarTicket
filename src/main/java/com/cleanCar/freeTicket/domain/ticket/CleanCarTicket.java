package com.cleanCar.freeTicket.domain.ticket;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;
import com.cleanCar.freeTicket.domain.gasStation.GasStationInfo;
import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "clean_car_ticket")
public class CleanCarTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clean_car_ticket_id")
    private Long CleanCarTicketId;

    @Column(name = "expired_dt", columnDefinition = "DATETIME COMMENT '무료세차권 사용 기한 일자'")
    private LocalDate expiredDt;

    @ManyToOne
    @JoinColumn(name = "client_car_info_id")
    private ClientCarInfo clientCarInfo;

    @OneToOne(mappedBy = "cleanCarTicket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GasStationInfo gasStationInfo;

    @Builder
    public CleanCarTicket(LocalDate expiredDt, ClientCarInfo clientCarInfo) {
        this.expiredDt = expiredDt;
        this.clientCarInfo = clientCarInfo;
    }

    public void setClientCarInfo(ClientCarInfo clientCarInfo) {
        this.clientCarInfo = clientCarInfo;
        clientCarInfo.getCleanCarTicketList().add(this);
    }

    public void setGasStationInfo(GasStationInfo gasStationInfo) {
        this.gasStationInfo = gasStationInfo;
        gasStationInfo.setCleanCarTicket(this);
    }
}
