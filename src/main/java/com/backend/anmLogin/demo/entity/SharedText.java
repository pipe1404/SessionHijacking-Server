package com.backend.anmLogin.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sharedtext")
@Getter
@Setter
public class SharedText {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User owner;

  @Column(name = "text_content", length = 100, nullable = false)
  private String text;
}
