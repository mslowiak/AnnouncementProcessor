package parser.walker.states;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import parser.walker.helpers.ProviderHelper;

import java.util.HashMap;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProcessPageState extends WalkerState {

    private HashMap<String, Object> pageWithAnnouncement;

    public ProcessPageState(ProviderHelper providerHelper, HashMap<String, Object> pageWithAnnouncement) {
        super(providerHelper);
        this.pageWithAnnouncement = pageWithAnnouncement;
    }

    @Override
    public WalkerState run() {
        return null;
    }
}
