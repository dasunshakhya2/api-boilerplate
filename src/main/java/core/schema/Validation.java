package core.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Validation {

    private List<String> validationErrors;


    public boolean isValid() {
        return this.validationErrors.isEmpty();
    }
}
