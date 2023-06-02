package com.cleanCar.freeTicket.admin.repository.impl;

import com.cleanCar.freeTicket.admin.dto.type.AdmSaveCleanCarTypeResponse;
import com.cleanCar.freeTicket.admin.dto.type.CleanCarTypeListResponseDTO;
import com.cleanCar.freeTicket.admin.repository.AdmCleanCarTypeRepositoryCustom;
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

import static com.cleanCar.freeTicket.admin.domain.QCleanCarType.cleanCarType;

@Repository
@RequiredArgsConstructor
public class AdmCleanCarTypeRepositoryCustomImpl implements AdmCleanCarTypeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
    @Override
    public Page<CleanCarTypeListResponseDTO> cleanCarTypeList(Pageable pageable, Long gasStationId) {

        List<CleanCarTypeListResponseDTO> list = queryFactory.select(Projections.constructor(CleanCarTypeListResponseDTO.class,
                        cleanCarType.cleanCarTypeId.as("cleanCarTypeId"),
                        cleanCarType.cleanType.as("cleanType"),
                        cleanCarType.price.as("price"),
                        cleanCarType.defaultCondition.as("defaultCondition"),
                        cleanCarType.gasStation.gasStationId.as("gasStationId")
                ))
                .from(cleanCarType)
                .where(cleanCarType.useYn.eq(UseYn.Y),
                        cleanCarType.delYn.eq(DelYn.N),
                        cleanCarType.gasStation.gasStationId.eq(gasStationId)
                        )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(list, pageable, () -> list.size());
    }

    @Transactional(readOnly = true)
    @Override
    public void deleteCleanCarType(Long cleanCarTypeId) {
        queryFactory.update(cleanCarType)
                .set(cleanCarType.useYn, UseYn.N)
                .set(cleanCarType.delYn, DelYn.Y)
                .where(cleanCarType.cleanCarTypeId.eq(cleanCarTypeId))
                .execute();
    }

    @Transactional(readOnly = true)
    @Override
    public AdmSaveCleanCarTypeResponse detailCleanCarType(Long cleanCarTypeId) {
        return queryFactory.select(Projections.constructor(AdmSaveCleanCarTypeResponse.class,
                        cleanCarType.cleanCarTypeId,
                        cleanCarType.cleanType,
                        cleanCarType.price,
                        cleanCarType.defaultCondition,
                        cleanCarType.gasStation.gasStationId
                ))
                .from(cleanCarType)
                .where(cleanCarType.useYn.eq(UseYn.Y),
                        cleanCarType.delYn.eq(DelYn.N),
                        cleanCarType.cleanCarTypeId.eq(cleanCarTypeId))
                .fetchOne();
    }
}
