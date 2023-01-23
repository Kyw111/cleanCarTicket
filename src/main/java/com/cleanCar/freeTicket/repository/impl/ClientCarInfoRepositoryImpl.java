package com.cleanCar.freeTicket.repository.impl;

import com.cleanCar.freeTicket.domain.clientCarInfo.ClientCarInfo;
import com.cleanCar.freeTicket.domain.clientCarInfo.QClientCarInfo;
import com.cleanCar.freeTicket.repository.ClientCarInfoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.cleanCar.freeTicket.domain.clientCarInfo.QClientCarInfo.clientCarInfo;

@Repository
@RequiredArgsConstructor
public class ClientCarInfoRepositoryImpl implements ClientCarInfoCustom {

    private final JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
    @Override
    public Optional<ClientCarInfo> getClientCarInfoByCarNumber(String carNumber) {
        return Optional.ofNullable(queryFactory.select(clientCarInfo)
                .from(clientCarInfo)
                .where(clientCarInfo.carNumber.eq(carNumber)).fetchOne());
    }
}
