package com.cleanCar.freeTicket.admin.repository.impl;

import com.cleanCar.freeTicket.admin.dto.station.GasStationDetailResponseDTO;
import com.cleanCar.freeTicket.admin.dto.station.GasStationListResponseDTO;
import com.cleanCar.freeTicket.admin.repository.AdmGasStationRepositoryCustom;
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

import java.util.List;

import static com.cleanCar.freeTicket.admin.domain.QGasStation.gasStation;

@Repository
@RequiredArgsConstructor
public class AdmGasStationRepositoryCustomImpl implements AdmGasStationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Transactional
    @Override
    public void deleteGasStations(List<Long> gasStationIds) {
        queryFactory.update(gasStation)
                .set(gasStation.useYn, UseYn.N)
                .set(gasStation.delYn, DelYn.Y)
                .where(gasStation.gasStationId.in(gasStationIds))
                .execute();
    }

    @Transactional(readOnly = true)
    @Override
    public GasStationDetailResponseDTO detailGasStation(Long gasStationId) {

        return queryFactory.select(Projections.constructor(GasStationDetailResponseDTO.class,
                        gasStation.gasStationId.as("gasStationId"),
                        gasStation.gasStationName.as("gasStationName"),
                        gasStation.gasStationAddress.as("gasStationAddress"),
                        gasStation.longX.as("longX"),
                        gasStation.latY.as("latY"),
                        gasStation.cleanCarFreePeriod.as("cleanCarFreePeriod")
                ))
                .from(gasStation)
                .where(gasStation.useYn.eq(UseYn.Y),
                        gasStation.delYn.eq(DelYn.N),
                        gasStation.gasStationId.eq(gasStationId))
                .fetchOne();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GasStationListResponseDTO> gasStationList(Pageable pageable) {

        List<GasStationListResponseDTO> list = queryFactory.select(Projections.constructor(GasStationListResponseDTO.class,
                        gasStation.gasStationId.as("gasStationId"),
                        gasStation.gasStationName.as("gasStationName")
                )).from(gasStation)
                .where(gasStation.useYn.eq(UseYn.Y),
                        gasStation.delYn.eq(DelYn.N))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(list, pageable, () -> list.size());
    }
}
