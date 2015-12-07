package fishingshop.validators;

import fishingshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ������ on 06.12.2015.
 */

@Component("usernameValidator")
@Scope("request")
public class UsernameValidator implements Validator {

    private static final String USERNAME_PATTERN="[a-zA-Z0-9-_]+";

    private Pattern pattern;
    private Matcher matcher;

    @Autowired
    private UserService userService;

    public UsernameValidator(){
        pattern=Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        try{
            if(userService.getUserByUsername(value.toString())!=null){
                throw new IllegalArgumentException("������������ � ����� ������ ��� ����������");
            }

            if (!Character.isLetter(value.toString().charAt(0))) {
                throw new IllegalArgumentException("��� ������������ ������ ���������� � �����");
            }

            if (!matcher.matches()) {
                throw new IllegalArgumentException("��� ������������ ����� ��������� ���������� �����, �����, - � _");
            }

            if(value.toString().length()<6){
                throw new IllegalArgumentException("��� ������������ �� ����� 6 ��������");
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
