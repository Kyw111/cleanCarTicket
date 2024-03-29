package com.cleanCar.freeTicket.admin.repository.impl;

import com.cleanCar.freeTicket.admin.dto.pay.ClientPayInfoListDTO;
import com.cleanCar.freeTicket.admin.repository.ClientPayInfoRepositoryCustom;
import com.cleanCar.freeTicket.utils.enums.DelYn;
import com.cleanCar.freeTicket.utils.enums.UseYn;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.cleanCar.freeTicket.admin.domain.QClientPayInfo.clientPayInfo;

/**
 * 고객 주유 정보 repository Impl
 */
@Repository
@RequiredArgsConstructor
public class ClientPayInfoRepositoryCustomImpl implements ClientPayInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 고객 주유 정보 목록
     */
    @Transactional(readOnly = true)
    @Override
    public Page<ClientPayInfoListDTO> clientPayInfoList(Long gasStationId, String carNumber, Pageable pageable) {

        List<ClientPayInfoListDTO> list = queryFactory.select(Projections.constructor(ClientPayInfoListDTO.class,
                        clientPayInfo.clientPayInfoId.as("clientPayInfoId"),
                        clientPayInfo.carNumber.as("carNumber"),
                        clientPayInfo.payOfGas.as("payOfGas"),
                        clientPayInfo.createdDt.as("createdDt"),
                        clientPayInfo.expiredDt.as("expiredDt"),
                        clientPayInfo.gasStation.gasStationId.as("gasStationId"),
                        clientPayInfo.gasStation.gasStationName.as("gasStationName")
                ))
                .from(clientPayInfo)
                .where(clientPayInfo.useYn.eq(UseYn.Y),
                        clientPayInfo.delYn.eq(DelYn.N),
                        clientPayInfo.gasStation.gasStationId.eq(gasStationId),
                        clientPayInfo.carNumber.eq(carNumber),
                        clientPayInfo.createdDt.before(clientPayInfo.expiredDt),
                        clientPayInfo.expiredDt.after(LocalDateTime.now()))
                .orderBy(clientPayInfo.createdDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(list, pageable, () -> list.size());
    }
}
