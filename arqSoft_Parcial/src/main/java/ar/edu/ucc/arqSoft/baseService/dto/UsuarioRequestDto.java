package ar.edu.ucc.arqSoft.baseService.dto;

import java.util.Set;

import ar.edu.ucc.arqSoft.baseService.model.Proyecto;
import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class UsuarioRequestDto implements DtoEntity {
			
			private String nombre;
			
			private String apellido;
			
			private String email;
		
			private Set<Proyecto> proyectos;

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public String getApellido() {
				return apellido;
			}

			public void setApellido(String apellido) {
				this.apellido = apellido;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public Set<Proyecto> getProyectos() {
				return proyectos;
			}

			public void setProyectos(Set<Proyecto> proyectos) {
				this.proyectos = proyectos;
			}
			
			
			
			
}
