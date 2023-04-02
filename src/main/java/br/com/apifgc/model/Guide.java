package br.com.apifgc.model;

import java.util.List;

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
	@Column @Nullable
	@OneToMany(mappedBy = "guide", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Strength> strengths;
	@Column @Nullable
	@OneToMany(mappedBy = "guide", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Weakness> weaknesses;
	@Column @Nullable
	@OneToMany(mappedBy = "guide", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Combo> combos;
}
