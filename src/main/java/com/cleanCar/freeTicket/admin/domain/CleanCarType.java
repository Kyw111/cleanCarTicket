package com.cleanCar.freeTicket.admin.domain;

import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 세차 가격 정보 도메인
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "clean_car_type")
public class CleanCarType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clean_car_type_id")
    private Long cleanCarTypeId;

    @Column(name = "clean_type", columnDefinition = "VARCHAR(30) COMMENT '세차타입명'")
    private String cleanType;

    @Column(name = "price", columnDefinition = "INTEGER(14)")
    private int price;

    @JoinColumn(name = "gas_station_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GasStation gasStation;

    @Builder
    public CleanCarType(String cleanType, int price, GasStation gasStation) {
        this.cleanType = cleanType;
        this.price = price;
        this.gasStation = gasStation;
    }

    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
        gasStation.getCleanCarTypeList().add(this);
    }
}
