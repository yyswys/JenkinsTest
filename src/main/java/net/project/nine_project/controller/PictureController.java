package net.project.nine_project.controller;

import net.project.nine_project.domain.Banner;
import net.project.nine_project.domain.Picture;
import net.project.nine_project.domain.Scenery;
import net.project.nine_project.service.PictureService;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("list")
    public Object pictureList(){
        List<Picture> pictureList=pictureService.listPicture();
        return JsonData.buildSuccess(pictureList);
    }

    @GetMapping("list_banner")       //轮播图
    public JsonData listBanner(){
        List<Banner> pictureBannerList=pictureService.listBanner();
        return JsonData.buildSuccess(pictureBannerList);
    }

    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "picture_id",required= true)int pictureId){
        Picture picture=pictureService.findDetailById(pictureId);//执行
        //picture值已传递，可在此执行操作
        //System.out.println(picture.getSceId());
        return JsonData.buildSuccess(picture);
    }

    @RequestMapping("list_picture_by_sce_id")
    public JsonData listPictureBySceId(@RequestParam(value = "sce_id",required= true)int sceId){
        List<Picture> pictureListBySceId=pictureService.listPictureBySceId(sceId);
        return JsonData.buildSuccess(pictureListBySceId);
    }

    @GetMapping("picture_to_scenery")
    public JsonData pictureToScenery(@RequestParam(value = "picture_id",required= true)int pictureId){
        Scenery scenery=pictureService.pictureToScenery(pictureId);//执行
        return JsonData.buildSuccess(scenery);
    }
}
