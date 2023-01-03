package com.demo.testprojectspring3java17.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "username_unique", columnNames = "username"))
@Builder
public class Users {

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@ToString.Exclude
	@Column(name = "password", nullable = false, columnDefinition = "text")
	private String password;

	@Column(name = "roles", nullable = true)
	private String roles;
}