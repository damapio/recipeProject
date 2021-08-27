package recipes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"}) //si no, crea una referencia circular
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autogen
	private Long id;

	@OneToOne
	private Recipe recipe;

	@Lob // Large object, String > 256 chars, CLOB
	private String recipeNotes;

}
