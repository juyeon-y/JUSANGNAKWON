package com.osakak.jusangnakwon.domain.liquor.application;

import com.osakak.jusangnakwon.domain.liquor.api.response.LiquorResponse;
import com.osakak.jusangnakwon.domain.liquor.dao.WineRepository;
import com.osakak.jusangnakwon.domain.liquor.dto.LiquorType;
import com.osakak.jusangnakwon.domain.liquor.dto.SearchType;
import com.osakak.jusangnakwon.domain.liquor.entity.Wine;
import com.osakak.jusangnakwon.domain.liquor.mapper.WineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LiquorService {
    private final WineRepository wineRepository;
    private final WineMapper wineMapper;

    public List<LiquorResponse> getLiquorList(LiquorType liquorType, SearchType searchType, Pageable pageable) {
        return getLiquorListBySearchType(liquorType, searchType, pageable);
    }

    private List<LiquorResponse> getLiquorListBySearchType(LiquorType liquorType, SearchType searchType, Pageable pageable) {
        switch (searchType) {
            case RANK:
                System.out.println("rank");
                return getLiquorListByType(liquorType, pageable);
            case RECOMM:
                System.out.println("recomm");
                return null;
        }
        return null;
    }

    private List<LiquorResponse> getLiquorListByType(LiquorType liquorType, Pageable pageable) {
        switch (liquorType) {
            case WHISKY:
                System.out.println("whisky");
                return null;
            case WINE:
                System.out.println("wine");
                Page<Wine> wines = wineRepository.findAll(pageable);
                return wineMapper.winesToSearchLiquorDtos(wines.getContent());
        }
        return null;
    }


}