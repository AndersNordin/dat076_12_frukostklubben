package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.UserEJB;
import dat076.frukostklubben.model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Julia Edited by: Anders, 2012-10-17
 */
@ManagedBean
@Named("signup")
@RequestScoped
public class SignupBB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @EJB
    UserEJB userRegistry;
    private User user = new User();
    private static final String EMAIL_REQUIRED = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";
    private static final String ZIP_REQUIRED = "[0-9]{5}";

    // ======================================
    // =           Public Methods           =
    // ======================================
    public void emailValidator(FacesContext context, UIComponent component,
            Object obj) throws ValidatorException {

        Pattern pattern = Pattern.compile(EMAIL_REQUIRED);
        Matcher matcher = pattern.matcher(obj.toString());

        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("E-mail "
                    + "validation failed.", " - Invalid E-mail");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    private String input1;
    private String input2;
    private boolean input1Set;

    public void pwValidator(FacesContext context, UIComponent component,
            Object obj) throws ValidatorException {

        if (input1Set) {
            input2 = (String) obj;
            if (input1 == null || input1.length() < 6 || (!input1.equals(input2))) {
                ((EditableValueHolder) component).setValid(false);
                context.addMessage(component.getClientId(context), new FacesMessage(
                        "Password must be 6 chars+ & both fields identical"));
            }
        } else {
            input1Set = true;
            input1 = (String) obj;
        }
    }

    public String doCreateUser() {
        try {
            user = userRegistry.createUser(user);
            return "index";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Stay on same page
        }
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================   

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
