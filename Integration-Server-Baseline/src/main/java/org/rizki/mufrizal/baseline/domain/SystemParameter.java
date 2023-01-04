package org.rizki.mufrizal.baseline.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_system_parameter")
public class SystemParameter implements Serializable {

    @Id
    @Column(name = "param_name", length = 100)
    private String paramName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "param_value", length = 100)
    private String paramValue;
}
