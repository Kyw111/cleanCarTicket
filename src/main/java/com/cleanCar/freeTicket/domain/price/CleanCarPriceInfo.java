package com.cleanCar.freeTicket.domain.price;

import com.cleanCar.freeTicket.domain.gasStation.GasStationInfo;
import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "clean_car_price_info")
public class CleanCarPriceInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clean_car_price_info_id")
    private Long cleanCarPriceInfo;

    @Column(name = "ticket_price", columnDefinition = "INT COMMENT '세차권 이용 요금'")
    private int ticketPrice;

    @Column(name = "normal_price", columnDefinition = "INT COMMENT '기본 요금'")
    private int normalPrice;

    @ManyToOne
    @JoinColumn(name = "gas_station_info_id")
    private GasStationInfo gasStationInfo;

    @Builder
    public CleanCarPriceInfo(Long cleanCarPriceInfo, int ticketPrice, int normalPrice, GasStationInfo gasStationInfo) {
        this.cleanCarPriceInfo = cleanCarPriceInfo;
        this.ticketPrice = ticketPrice;
        this.normalPrice = normalPrice;
        this.gasStationInfo = gasStationInfo;
    }

    public void setGasStationInfo(GasStationInfo gasStationInfo) {
        this.gasStationInfo = gasStationInfo;
        gasStationInfo.getCleanCarPriceInfoList().add(this);
    }
}

