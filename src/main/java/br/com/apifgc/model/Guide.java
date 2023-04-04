package br.com.apifgc.model;

import java.util.List;

import br.com.apifgc.dto.combo.ComboData;
import br.com.apifgc.dto.guide.GuideRegistrationData;
import br.com.apifgc.dto.guide.GuideUpdateData;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
public class Guide {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fighter_id", nullable = false)
	private Fighter fighter;
	@Column @NotNull
	private String name;
	@Column @NotNull
	private String description;
	@Column @Nullable
	private List<String> strengths;
	@Column @Nullable
	private List<String> weaknesses;
	@Column @Nullable
	@OneToMany(mappedBy = "guide", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Combo> combos;
	
	public Guide(@Valid GuideRegistrationData data, Fighter fighter) {
		this.fighter = fighter;
		this.name = data.name();
		this.description = data.description();
		if (data.strengths() != null) {
			this.strengths = data.strengths();
		}
		if (data.weaknesses() != null) {
			this.weaknesses = data.weaknesses();
		}
	}

	public void updateData(@Valid GuideUpdateData data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		if (data.description() != null) {
			this.description = data.description();
		}
		if (data.strengths() != null) {
			this.strengths = data.strengths();
		}
		if (data.weaknesses() != null) {
			this.weaknesses = data.weaknesses();
		}
		if (data.combos() != null) {
			for (ComboData comboData : data.combos()) {
				this.combos.add(new Combo(comboData.combo(), comboData.url()));
			}
		}
	}
}
