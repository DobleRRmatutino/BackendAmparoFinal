package pe.edu.upc.examenfinal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String especialidad_nombre;
    @Column(nullable = false)
    private String numero_colegiatura;
    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Users usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad_nombre() {
        return especialidad_nombre;
    }

    public void setEspecialidad_nombre(String especialidad_nombre) {
        this.especialidad_nombre = especialidad_nombre;
    }

    public String getNumero_colegiatura() {
        return numero_colegiatura;
    }

    public void setNumero_colegiatura(String numero_colegiatura) {
        this.numero_colegiatura = numero_colegiatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
}
