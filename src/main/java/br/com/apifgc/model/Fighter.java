package br.com.apifgc.model;

import java.util.List;

import br.com.apifgc.dto.fighter.FighterRegistrationData;
import br.com.apifgc.dto.fighter.FighterUpdateData;
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
public class Fighter {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;
	@Column @NotNull
	private String name;
	@Column @NotNull
	private String urlImage;
	@OneToMany(mappedBy = "fighter", fetch = FetchType.LAZY)
	private List<Guide> guides;
	
	public Fighter(@Valid FighterRegistrationData data,@Valid Game game) {
		this.game = game;
		this.name = data.name();
		this.urlImage = data.urlImage();
	}

	public void updateData(@Valid FighterUpdateData data, Game game) {
		if(data.game_id() != null) {
			this.game = game;
		}
		if(data.name() != null) {
			this.name = data.name();
		}
		if(data.urlImage() != null) {
			this.urlImage = data.urlImage();
		}
	}
}
