package PR15.Application.model;
import PR15.Application.model.Manufacture;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.*;

@Entity
@Table(name = "Phones")
public class Phone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @CreationTimestamp
    @Column(name = "creation_year")
    private LocalDateTime creationYear;

    @Column(name = "owner")
    @NotNull
    private UUID owner;


    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manufacture> manufactures = new ArrayList<>();


    public Phone() {

    }

    public Phone(String name, UUID owner, LocalDateTime creationYear) {
        this.name = name;
        this.owner = owner;
        this.creationYear = creationYear;
    }

    public void addManufacture(Manufacture manufacture) {
        manufactures.add(manufacture);
        manufacture.setUser(this);
    }
    public void removeManufacture(Manufacture manufacture) {
        manufactures.remove(manufacture);
        manufacture.setUser(null);
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationYear() {
        return creationYear;
    }

    public UUID getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Manufacture #" + id + " " + owner + " " + creationYear;
    }
}
