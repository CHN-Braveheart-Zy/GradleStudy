import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        AppExtension app = project.extensions.getByType(AppExtension.class)
        app.registerTransform(new MyTransform())
    }
}