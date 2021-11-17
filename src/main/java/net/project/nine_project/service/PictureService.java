package net.project.nine_project.service;

import net.project.nine_project.domain.Banner;
import net.project.nine_project.domain.Picture;
import net.project.nine_project.domain.Scenery;

import java.util.List;

public interface PictureService {
    List<Picture> listPicture();
    List<Banner> listBanner();
    Picture findDetailById(int pictureId);
    List<Picture> listPictureBySceId(int sceId);
    Scenery pictureToScenery(int pictureId);
}
