package Model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
@Entity(name = "channel")
public class Channel extends PanacheEntityBase {
    @Id
    @Column(name = "apikey", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String apikey;
    public String tittle;
    public String description;
    public String owner;
    @OneToMany
    @JoinColumn(name = "apikey_id",referencedColumnName = "apikey")
    public List<Sensor> sensorList;
}
