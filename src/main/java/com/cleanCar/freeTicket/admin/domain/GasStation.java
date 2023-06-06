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
    private Long gasStationId;

    @Column(name = "gas_station_name", columnDefinition = "VARCHAR(60) COMMENT '주유소명'")
    private String gasStationName;

    @Column(name = "gas_station_address", columnDefinition = "VARCHAR(255) COMMENT '주유소 주소'")
    private String gasStationAddress;

    @Column(name = "clean_car_free_period", columnDefinition = "INTEGER(5) COMMENT '무료 세차 가능 기한'")
    private int cleanCarFreePeriod;

    @Column(name = "X", columnDefinition = "VARCHAR(20) COMMENT '경도'")
    private String longX;

    @Column(name = "Y", columnDefinition = "VARCHAR(20) COMMENT '위도'")
    private String latY;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "gasStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CleanCarType> cleanCarTypeList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "gasStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientPayInfo> clientPayInfoList = new ArrayList<>();


    @Builder
    public GasStation(String gasStationName, String gasStationAddress, int cleanCarFreePeriod, String longX, String latY) {
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        this.cleanCarFreePeriod = cleanCarFreePeriod;
        this.longX = longX;
        this.latY = latY;
    }

    public void setCleanCarTypeList(List<CleanCarType> cleanCarTypeList) {
        cleanCarTypeList.stream().forEach(cleanCarTypeInfo -> {
            cleanCarTypeInfo.setGasStation(this);
        });
    }

    public void setClientPayInfoList(List<ClientPayInfo> clientPayInfoList) {
        clientPayInfoList.stream().forEach(clientPayInfo -> {
            clientPayInfo.setGasStation(this);
        });
    }

    public void setCleanCarFreePeriod(int cleanCarFreePeriod) {
        this.cleanCarFreePeriod = cleanCarFreePeriod;
    }

    // 관리자 - 주유소 정보 수정 API 에 사용
    public void updateGasStation(String gasStationName, String gasStationAddress, String longX, String latY) {
        this.gasStationName = gasStationName;
        this.gasStationAddress = gasStationAddress;
        // todo : 무료사용기간 관련 로직 추가 개발 필요
        this.longX = longX;
        this.latY = latY;
    }
}
