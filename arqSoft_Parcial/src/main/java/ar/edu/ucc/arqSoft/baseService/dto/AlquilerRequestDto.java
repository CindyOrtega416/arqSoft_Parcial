package ar.edu.ucc.arqSoft.baseService.dto;

import ar.edu.ucc.arqSoft.common.dto.DtoEntity;

public class AlquilerRequestDto implements DtoEntity {

		private Long socioId;
		
		private Long peliculaId;

		public Long getSocioId() {
			return socioId;
		}

		public void setSocioId(Long socioId) {
			this.socioId = socioId;
		}

		public Long getPeliculaId() {
			return peliculaId;
		}

		public void setPeliculaId(Long peliculaId) {
			this.peliculaId = peliculaId;
		}
		
}
