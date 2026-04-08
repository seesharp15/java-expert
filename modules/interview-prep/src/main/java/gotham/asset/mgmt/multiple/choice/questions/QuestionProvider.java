package gotham.asset.mgmt.multiple.choice.questions;

import java.util.List;

public interface QuestionProvider {
    List<Question> loadAll();
}
