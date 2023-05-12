package com.cleanCar.freeTicket.admin.domain;

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

/**
 * 주유소 정보 도메인
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "gas_station")
public class GasStation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gas_station_id")
    private Long gasStationInfoId;

    @Column(name = "gas_station_name", columnDefinition = "VARCHAR(60) COMMENT '주유소명'")
    private String gasStationName;

    @Column(name = "gas_station_address", columnDefinition = "VARCHAR(255) COMMENT '주유소 주소'")
    private String gasStationAddress;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "gasStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CleanCarType> cleanCarTypeList = new ArrayList<>();


    @Builder
    public GasStation(String gasStationName, String gasStationAddress, List<CleanCarType> cleanCarTypeList) {
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        this.cleanCarTypeList = cleanCarTypeList;
    }

    public void setCleanCarTypeList(List<CleanCarType> cleanCarTypeList) {
        cleanCarTypeList.stream().forEach(cleanCarTypeInfo -> {
            cleanCarTypeInfo.setGasStation(this);
        });
    }
}
