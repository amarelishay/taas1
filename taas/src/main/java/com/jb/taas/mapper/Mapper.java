package com.jb.taas.mapper;

import java.util.List;

/**
 * Created by kobis on 12 May, 2022
 */
public interface Mapper <DAO,DTO> {

    DAO toDao(DTO dto);
    DTO toDto(DAO dao);

    List<DAO> toDaoList(List<DTO> dtos);
    List<DTO> toDtoList(List<DAO> daos);
}
