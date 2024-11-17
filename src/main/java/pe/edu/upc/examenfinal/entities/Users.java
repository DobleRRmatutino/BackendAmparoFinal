package pe.edu.upc.examenfinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "users")
public class Users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, unique = true)
	private String username;
	@Column(length = 200)
	private String password;
	private Boolean enabled;
	@Column(length = 200)
	private String name;
	@Column(length = 200)
	private String surnames;
	@Column(length = 200)
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	@JsonManagedReference
	private Role role;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Comentario> comentarios;


	public Users() {
	}

	@JsonCreator
	public Users(@JsonProperty("username") String username) {
		this.username = username;
	}
}
