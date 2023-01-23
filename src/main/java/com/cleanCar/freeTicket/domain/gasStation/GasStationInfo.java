package com.cleanCar.freeTicket.domain.gasStation;

import com.cleanCar.freeTicket.domain.price.CleanCarPriceInfo;
import com.cleanCar.freeTicket.domain.ticket.CleanCarTicket;
import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "gasStationInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CleanCarPriceInfo> cleanCarPriceInfoList = new ArrayList<>();


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

    public void setCleanCarPriceInfoList(List<CleanCarPriceInfo> cleanCarPriceInfoList) {
        cleanCarPriceInfoList.stream().forEach(cleanCarPriceInfo -> {
            cleanCarPriceInfo.setGasStationInfo(this);
        });
    }
}
