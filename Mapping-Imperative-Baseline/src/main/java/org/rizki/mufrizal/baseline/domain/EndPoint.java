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
@Table(name = "tb_end_point",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"backend", "backend_function"})
        },
        indexes = {
                @Index(name = "idx_backend", columnList = "backend"),
                @Index(name = "idx_backend_function", columnList = "backend_function")
        })
public class EndPoint implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "backend", length = 10)
    private String backend;

    @Column(name = "backend_function", length = 15)
    private String backendFunction;

    @Column(name = "url", length = 200)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", length = 6)
    private HttpMethod method;

    @Column(name = "timeout", length = 6)
    private Integer timeout;

    @Column(name = "connect_timeout", length = 6)
    private Integer connectTimeout;
}
