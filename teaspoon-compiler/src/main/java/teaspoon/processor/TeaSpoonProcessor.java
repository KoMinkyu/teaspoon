package teaspoon.processor;

import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import teaspoon.annotations.OnBackground;
import teaspoon.annotations.OnUi;

@AutoService(Processor.class)
public class TeaSpoonProcessor extends AbstractProcessor{

    private Elements envElementsUtil;
    private Types envTypesUtil;
    private Filer envFiler;

    @Override public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        envElementsUtil = env.getElementUtils();
        envTypesUtil = env.getTypeUtils();
        envFiler = env.getFiler();
    }

    @Override public Set<String> getSupportedAnnotationTypes() {
        final Set<String> annotationTypes = new LinkedHashSet<>();

        annotationTypes.add(OnUi.class.getCanonicalName());
        annotationTypes.add(OnBackground.class.getCanonicalName());

        return annotationTypes;
    }

    @Override public boolean process(Set<? extends TypeElement> elements, RoundEnvironment env) {
        findAndParseTargets(env);
        return false;
    }

    private void findAndParseTargets(RoundEnvironment env) {
        for (Element element : env.getElementsAnnotatedWith(OnUi.class)) {

        }
    }
}
