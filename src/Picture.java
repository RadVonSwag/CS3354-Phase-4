import java.rmi.server.UID;

public class Picture {

    private int pictureUID;
    private String pictureFilePath;
    public Picture(int id, String filePath){
        pictureUID=id;
        pictureFilePath=filePath;
    }
    public boolean equalsPic(Picture p){
        return p.pictureUID==this.pictureUID;
    }
}
