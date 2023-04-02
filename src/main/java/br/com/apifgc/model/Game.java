package br.com.apifgc.model;

import java.util.List;

import br.com.apifgc.dto.game.GameRegistrationData;
import br.com.apifgc.dto.game.GameUpdateData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class Game {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_game;
	@Column @NotNull @NotEmpty
	private String name;
	@Column @NotNull
	private String urlImage;
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	private List<Fighter> characteres;
	
	public Game(GameRegistrationData data) {
		this.name = data.name();
		this.urlImage = data.urlImage();
	}

	public void updateData(@Valid GameUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.urlImage() != null) {
            this.urlImage = data.urlImage();
        }
	}
		
}
