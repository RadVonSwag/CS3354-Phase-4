import java.rmi.server.UID;

public class Picture {

    private int pictureUID;

    public Picture(int id){
        pictureUID=id;
    }
    public boolean equalsPic(Picture p){
        return p.pictureUID==this.pictureUID;
    }
}
