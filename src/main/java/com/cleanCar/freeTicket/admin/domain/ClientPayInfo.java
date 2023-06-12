package com.cleanCar.freeTicket.admin.domain;

import com.cleanCar.freeTicket.admin.dto.pay.UpdateClientPayInfoDTO;
import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 고객 주유 정보 도메인
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "client_pay_info")
public class ClientPayInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_pay_info_id")
    private Long clientPayInfoId;

    @Column(name = "car_number", columnDefinition = "VARCHAR(10) COMMENT '고객 차량번호'")
    private String carNumber;

    @Column(name = "pay_of_gas", columnDefinition = "INTEGER(15) COMMENT '고객 주유 가격 정보'")
    private int payOfGas;

    @Column(name = "expired_dt", columnDefinition = "DATETIME COMMENT '사용 기한'")
    private LocalDateTime expiredDt;

    @JoinColumn(name = "gas_station_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GasStation gasStation;

    @Builder
    public ClientPayInfo(String carNumber, int payOfGas, LocalDateTime expiredDt, GasStation gasStation) {
        this.carNumber = carNumber;
        this.payOfGas = payOfGas;
        this.expiredDt = expiredDt;
        this.gasStation = gasStation;
    }

    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
        gasStation.getClientPayInfoList().add(this);
    }

    // 고객 주유 정보 수정 API 에서 사용
    public void updateClientPayInfo(UpdateClientPayInfoDTO updateClientPayInfoDTO) {
        this.carNumber = updateClientPayInfoDTO.carNumber();
        this.payOfGas = updateClientPayInfoDTO.payOfGas();
    }
}
