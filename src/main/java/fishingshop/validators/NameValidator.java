package fishingshop.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Сергей on 06.12.2015.
 */

@FacesValidator("nameValidator")
public class NameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {

        try{
            if (!Character.isLetter(value.toString().charAt(0)) && uiComponent.getId().equals("name")) {
                throw new IllegalArgumentException("Имя должно начинаться с буквы");
            }

            if (!Character.isLetter(value.toString().charAt(0)) && uiComponent.getId().equals("surname")) {
                throw new IllegalArgumentException("Фамилия должна начинаться с буквы");
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
