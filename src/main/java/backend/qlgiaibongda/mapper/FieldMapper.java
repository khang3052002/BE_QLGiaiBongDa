package backend.qlgiaibongda.mapper;

import backend.qlgiaibongda.dto.FieldDTO;
import backend.qlgiaibongda.entity.SanBongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "tenSan", source = "dto.tenSan")
    @Mapping(target = "diaDiem", source = "dto.diaDiem")
    SanBongEntity toEntity(FieldDTO dto);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "tenSan", source = "entity.tenSan")
    @Mapping(target = "diaDiem", source = "entity.diaDiem")
    FieldDTO toDTO(SanBongEntity entity);
}
