package library360.livroms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "users")
public class User extends BaseEntity<Long> {

}
