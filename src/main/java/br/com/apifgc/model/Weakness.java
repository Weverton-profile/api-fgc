package br.com.apifgc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Weakness {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String weakness;
	@ManyToOne
	@JoinColumn(name = "guide_id", nullable = false)
	private Guide guide;
}
