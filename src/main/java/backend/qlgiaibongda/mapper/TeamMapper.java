package backend.qlgiaibongda.mapper;


import backend.qlgiaibongda.dto.TeamDTO;
import backend.qlgiaibongda.entity.DoiBongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses = FieldMapper.class)
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    @Mapping(target = "hinhAnh", source = "dto.hinhAnh")
    @Mapping(target = "ten", source = "dto.ten")
    @Mapping(target = "namThanhLap", source = "dto.namThanhLap")
    DoiBongEntity updateEntity(TeamDTO dto, @MappingTarget DoiBongEntity entity);

    default DoiBongEntity toEntity(TeamDTO dto, @MappingTarget DoiBongEntity entity) {
        if (entity == null) {
            entity = new DoiBongEntity();
        }
        updateEntity(dto, entity);
        return entity;
    }


    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "hinhAnh", source = "entity.hinhAnh")
    @Mapping(target = "ten", source = "entity.ten")
    @Mapping(target = "namThanhLap", source = "entity.namThanhLap")
    @Mapping(target = "fieldDTO", source = "entity.sanBong")
    TeamDTO toDTO(DoiBongEntity entity);


}
