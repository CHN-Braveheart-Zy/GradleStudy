import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager

class MyTransform extends Transform{

    @Override
    String getName() {
        return "Zhangjunpu"
    }

    /**
     * 接受类型
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 接受class范围  jia? 三方  aar?  libs
     * @return
     */
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 是否增量
     * @return
     */
    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        TransformOutputProvider provider = transformInvocation.outputProvider
        Collection<TransformInput> inputs = transformInvocation.inputs
        inputs.each { it ->
            def jarInputs = it.jarInputs
            def dirs = it.directoryInputs
            dirs.each {
                it.changedFiles //改变了的文件
            }
        }
    }
}