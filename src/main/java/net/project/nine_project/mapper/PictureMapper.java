package net.project.nine_project.mapper;

import net.project.nine_project.domain.Banner;
import net.project.nine_project.domain.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper {
    //@Param用作参数的对应，多参数使用，这里可加可不加
    /**
     * 查询视频列表
     * @return
     */
    List<Picture> listPicture();
    List<Banner> listBanner();
    Picture findDetailById(int pictureId);
    List<Picture> listPictureBySceId(int sceId);
}