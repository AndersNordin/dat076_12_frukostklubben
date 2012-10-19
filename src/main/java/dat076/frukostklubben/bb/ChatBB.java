/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.icefaces.application.PushRenderer;

/**
 *
 * @author Fredrik
 */
@ApplicationScoped
@Named("chatBB")
public class ChatBB {
    private List<String> list = new ArrayList<>();
    private String text;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String post(){
        list.add(text);
        return "chat?faces-redirect=true";
    }
}
