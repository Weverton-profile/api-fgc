package br.com.apifgc.model;

import br.com.apifgc.dto.combo.ComboRegistrationData;
import br.com.apifgc.dto.combo.ComboUpdateData;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Combo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "guide_id", nullable = false)
	private Guide guide;
	@Column @NotNull
	private String combo;
	@Column @Nullable
	private String urlVideo;
	
	public Combo(ComboRegistrationData combos, Guide guide) {
		this.guide = guide;
		this.combo = combos.combo();
		if (combos.url() != null) {
			this.urlVideo = combos.url();
		}
	}

	public Combo(String combo, String urlVideo) {
		this.combo = combo;
		this.urlVideo = urlVideo;
	}

	public void updateData(ComboUpdateData combos) {
		if (combos.combo() != null) {
			this.combo = combos.combo();
		}
		if (combos.urlVideo() != null) {
			this.urlVideo = combos.urlVideo();
		}
	}
}
