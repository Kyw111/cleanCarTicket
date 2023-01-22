package com.cleanCar.freeTicket.gasStation;

import com.cleanCar.freeTicket.ticket.CleanCarTicket;
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
@Table(name = "gas_station_info")
public class GasStationInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gas_station_info_id")
    private Long gasStationInfoId;

    @Column(name = "gas_station_name", columnDefinition = "VARCHAR(60) COMMENT '주유소명'")
    private String gasStationName;

    @Column(name = "gas_station_address", columnDefinition = "VARCHAR(255) COMMENT '주유소 주소'")
    private String gasStationAddress;

    @OneToOne
    @JoinColumn(name = "clean_car_ticket_id")
    private CleanCarTicket cleanCarTicket;

    @Builder
    public GasStationInfo(String gasStationName, String gasStationAddress, CleanCarTicket cleanCarTicket) {
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        this.cleanCarTicket = cleanCarTicket;
    }

    public void setCleanCarTicket(CleanCarTicket cleanCarTicket) {
        this.cleanCarTicket = cleanCarTicket;
        cleanCarTicket.setGasStationInfo(this);
    }
}
