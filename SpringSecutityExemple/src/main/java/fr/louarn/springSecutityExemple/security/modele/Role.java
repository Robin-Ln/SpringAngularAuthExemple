package fr.louarn.springSecutityExemple.security.modele;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {

    /**
     * Attibutes
     */

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tb_sequence")
    @Column(name="i_r_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "v_r_name", unique = true)
    private RoleName name;

    /**
     * Constructeurs
     */

    public Role() {}

    /**
     * Accesseurs
     */

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
