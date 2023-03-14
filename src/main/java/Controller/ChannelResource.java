package Controller;
import Model.Channel;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
@Path("channel")
public class ChannelResource {
    @GET
    public List<Channel> getChannel(){
        return Channel.listAll();
    }
    @POST
    @Transactional
    public Channel addChannel(Channel channel){
        channel.persist();
        return channel;
    }
}