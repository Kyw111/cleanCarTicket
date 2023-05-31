package com.cleanCar.freeTicket.admin.domain;

import com.cleanCar.freeTicket.admin.dto.type.AdmUpdateCleanCarTypeDTO;
import com.cleanCar.freeTicket.utils.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 세차 종류 및 가격정보 도메인
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

    @Column(name = "default_condition", columnDefinition = "VARCHAR(50) COMMENT '기본 조건'")
    private String defaultCondition;

    @JoinColumn(name = "gas_station_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GasStation gasStation;

    @Builder
    public CleanCarType(String cleanType, int price, String defaultCondition, GasStation gasStation) {
        this.cleanType = cleanType;
        this.price = price;
        this.defaultCondition = defaultCondition;
        this.gasStation = gasStation;
    }

    public void setGasStation(GasStation gasStation) {
        this.gasStation = gasStation;
        gasStation.getCleanCarTypeList().add(this);
    }

    // 관리자 - 세차 종류 및 가격 정보 수정 API 에서 사용
    public void updateCleanCarType(AdmUpdateCleanCarTypeDTO admUpdateCleanCarTypeDTO) {
        this.cleanType = admUpdateCleanCarTypeDTO.cleanType();
        this.price = admUpdateCleanCarTypeDTO.price();
        this.defaultCondition = admUpdateCleanCarTypeDTO.defaultCondition();
    }
}
