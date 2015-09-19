package fishingshop.ui;

import fishingshop.domain.goods.Goods;
import fishingshop.service.GoodsService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Сергей on 08.09.2015.
 */

@Service
@Scope("request")
public class ShowImage {

    @Autowired
    private GoodsService goodsService;

    public StreamedContent getImage() throws IOException{
        FacesContext context=FacesContext.getCurrentInstance();
        if(context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE){
            return new DefaultStreamedContent();
        } else {
            int imageId=Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("goodsId"));
            Goods goods=goodsService.getGoodsById(imageId);
            return  new DefaultStreamedContent(new ByteArrayInputStream(goods.getImage()));
        }
    }
}
