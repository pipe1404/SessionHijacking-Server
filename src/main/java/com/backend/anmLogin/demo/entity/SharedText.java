package com.backend.anmLogin.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sharedtext")
@Getter
@Setter
public class SharedText {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private UUID id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @Column(name = "text_content", length = 2000, nullable = false)
  private String text;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @PrePersist
  public void createdAtUpdate() {
    createdAt = Timestamp.from(Instant.now());
  }
}
