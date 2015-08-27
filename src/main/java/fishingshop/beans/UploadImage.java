package fishingshop.beans;

import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("request")
public class UploadImage {

    private UploadedFile file;
    private byte[] image;

    public  byte[] upload(){
        if(file !=null){
            image=file.getContents();
        }
        return image;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public byte[] getImage() {
        return image;
    }
}
