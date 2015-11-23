package fishingshop.beans;

import fishingshop.controller.CatalogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Сергей on 20.11.2015.
 */

@Component
@Scope("request")
public class NavigationBean implements Serializable {

    @Autowired
    private CatalogController catalogController;

    public String showIndex(){
        catalogController.updateGoodsList();
        return "/public/index.xhtml?faces-redirect=true";
    }

    public String showPersonalArea(){
        return "/public/personal-area.xhtml?faces-redirect=true";
    }

    public String showRegistration(){
        return "/public/registration.xhtml?faces-redirect=true";
    }

    public String showLogin(){
        return "/public/login.xhtml?faces-redirect=true";
    }

    public String showAdminArea(){
        return "/private/admin-area.xhtml?faces-redirect=true";
    }

}
