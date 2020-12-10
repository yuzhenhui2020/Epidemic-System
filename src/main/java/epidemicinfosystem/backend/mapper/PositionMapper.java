package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.Position;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PositionMapper {

    @Select("select position from position where province=#{province} and city=#{city} and area=#{area}")
    Integer findPosition(Position position);

    @Select("select city from position where position=#{position}")
    String getCity(int position);
    @Insert("insert into position values(null,#{province},#{city},#{area})")
    void addPosition(Position position);
}
