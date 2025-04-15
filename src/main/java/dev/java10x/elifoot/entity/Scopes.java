package dev.java10x.elifoot.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scopes")
public class Scopes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scopes_seq")
    @SequenceGenerator(name = "scopes_seq", sequenceName = "scopes_seq", allocationSize = 1)
    private Long id;
    private String name;

}
