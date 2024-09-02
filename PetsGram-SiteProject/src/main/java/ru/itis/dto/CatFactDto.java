package ru.itis.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.model.CatFact;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatFactDto {
    private String fact;
    private int length;

    public CatFact formCatFactDto() {
        CatFact catFact = new CatFact();
        catFact.setFact(this.fact);
        catFact.setLength(this.length);
        return catFact;
    }

}