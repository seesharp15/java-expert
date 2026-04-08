package gotham.asset.mgmt.multiple.choice.questions.debugging.universal;

import gotham.asset.mgmt.multiple.choice.questions.Question;

import java.util.Set;

public abstract class UniversalCodeQuestion extends Question {

    @Override
    public Set<String> getApplicableLanguages() {
        return Set.of("java", "dotnet", "scala");
    }
}
