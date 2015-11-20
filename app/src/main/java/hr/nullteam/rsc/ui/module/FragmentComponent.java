package hr.nullteam.rsc.ui.module;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = {ActivityComponent.class},
        modules = { FragmentModule.class }
)
public interface FragmentComponent extends FragmentComponentInjects {

    final class Initializer {
        static public FragmentComponent init(ActivityComponent activityComponent) {
            return DaggerFragmentComponent.builder()
                    .activityComponent(activityComponent)
                    .fragmentModule(new FragmentModule())
                    .build();
        }

        private Initializer() {
        }
    }
}
