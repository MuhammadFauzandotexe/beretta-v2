package Model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name = "sensor")
public class Sensor extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private Long id;
    @JsonGetter
    public Long getId() {return id;}
    @JsonIgnore
    public void setId(Long id) {this.id = id;}
    public String field1;
    public String field2;
    public String field3;
    public String field4;
    public String field5;
    public String field6;
    public String field7;
}
