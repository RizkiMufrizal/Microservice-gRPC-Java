package org.rizki.mufrizal.baseline.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_harmonized",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"backend", "backend_code"})
        },
        indexes = {
                @Index(name = "fn_backend", columnList = "backend"),
                @Index(name = "fn_backend_code", columnList = "backend_code")
        })
public class Harmonized implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "backend", length = 10)
    private String backend;

    @Column(name = "backend_code", length = 10)
    private String backendCode;

    @Column(name = "backend_desc", length = 50)
    private String backendDescription;

    @Column(name = "harmonized_code", length = 10)
    private String harmonizedCode;

    @Column(name = "harmonized_desc", length = 50)
    private String harmonizedDescription;

    @Column(name = "harmonized_http_status", length = 3)
    private Integer harmonizedHttpStatus;

    @Column(name = "is_error")
    private Boolean isError;

}
