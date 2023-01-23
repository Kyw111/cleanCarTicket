package com.cleanCar.freeTicket.domain.clientCarInfo;

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
@Table(name = "client_car_info")
public class ClientCarInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_car_info_id")
    private Long clientCarInfoId;

    @Column(name = "car_number", columnDefinition = "VARCHAR(16) COMMENT '차량번호'")
    private String carNumber;

    @Column(name = "car_model", columnDefinition = "VARCHAR(50) COMMENT '차량기종'")
    private String carModel;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "clientCarInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CleanCarTicket> cleanCarTicketList = new ArrayList<>();

    @Builder
    public ClientCarInfo(String carNumber, String carModel) {
        this.carNumber = carNumber;
        this.carModel = carModel;
    }
}
