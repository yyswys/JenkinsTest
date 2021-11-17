package net.project.nine_project.service.impl;

import net.project.nine_project.domain.Banner;
import net.project.nine_project.domain.Picture;
import net.project.nine_project.domain.Scenery;
import net.project.nine_project.mapper.PictureMapper;
import net.project.nine_project.mapper.SceneryMapper;
import net.project.nine_project.mapper.UserMapper;
import net.project.nine_project.service.PictureService;
import net.project.nine_project.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private BaseCache baseCache;
    @Autowired
    private SceneryMapper sceneryMapper;

    public PictureMapper getPictureMapper() {
        return pictureMapper;
    }

    @Override
    public String toString() {
        return "PictureServiceImpl{" +
                "pictureMapper=" + pictureMapper +
                '}';
    }

    public void setPictureMapper(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }

    @Override
    public List<Picture> listPicture() {
        return pictureMapper.listPicture();
    }

    @Override
    public List<Banner> listBanner() {
        try{
            Object cacheObj = baseCache.getTenMinCache().get("index:banner",()->{
                List<Banner> bannerList = pictureMapper.listBanner();
                System.out.println("从数据库查找...");
                return bannerList;
            });
            if(cacheObj instanceof List){
                List<Banner> BannerList=(List<Banner>)cacheObj;
                return BannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //原本只有这一条
        //return videoMapper.listBanner();
        return null;
    }

    @Override
    public Picture findDetailById(int pictureId) {
        //需要mybatis关联复杂查询，后续完善
        return pictureMapper.findDetailById(pictureId);
    }

    @Override
    public List<Picture> listPictureBySceId(int sceId)
    {
        List<Picture> list=pictureMapper.listPictureBySceId(sceId);
        list.sort(Comparator.comparing(Picture::getCreateTime).reversed());
        return list;
    }

    @Override
    public Scenery pictureToScenery(int pictureId)
    {
        Picture picture=pictureMapper.findDetailById(pictureId);
        return sceneryMapper.findById(picture.getSceId());
    }
}
