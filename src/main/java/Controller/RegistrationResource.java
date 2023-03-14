package Controller;
import Model.UserData;
import Util.Bash64Token;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Path("register")
public class RegistrationResource {
    @GET
    @PermitAll
    public List<UserData> getData(){
        return UserData.listAll();
    }
    @Inject
    Mailer mailer;
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(JsonObject body) {
        String status = null;
        String emailPattern = "^((?!\\.)[\\w_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])";
        String Domain = "http://localhost:8080/verify/";
        UserData userData = new UserData();
        String username = body.getString("username");
        String email = body.getString("email");
        if(bacadata(email,emailPattern)==true){
            userData.password = body.getString("password");
            userData.persist();
            if(userData.isPersistent()){
                Bash64Token bash64Token = new Bash64Token(userData.id+"&="+email+"&="+username);
                String subject = "Hello "+username+", immediately verify your Beretta account";
                String message = "Hello " +username+".\n" +
                        "Thank you for registering as a member of Beretta Indonesia.\n" +
                        "Next, let's verify your account via the following link: \n"+
                        Domain+bash64Token.getBash64();
                mailer.send(Mail.withText(email, "Beretta Email Verification",message));
                status = "successful registration \n" +
                        "verify your account via the link we sent to your email address \n" +
                        "the link will expire after 24 hours.\n";
            }
        }
        else {
            status = "your email address is invalid";
        }
        return status;
    }
    boolean bacadata(String email,String pattern){
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher emailMatc = emailPattern.matcher(email);
        boolean b = emailMatc.matches();
        return b;
    }
}