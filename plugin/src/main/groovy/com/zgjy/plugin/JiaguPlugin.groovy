package com.zgjy.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.builder.model.SigningConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

class JiaguPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def jiagu = project.extensions.create('jiagu', Jiagu.class)
        AppExtension android = project.extensions.android as AppExtension
        project.afterEvaluate {
            android.applicationVariants.all {
                ApplicationVariant variant ->
                    variant.outputs.all {
                        BaseVariantOutput output ->
                            SigningConfig signingConfig = variant.signingConfig
                            def apkFile = output.outputFile
                            //创建加固任务
                            JiaguTask task = project.tasks.create("jiaguTask${variant.baseName.capitalize()}", JiaguTask.class)
                            task.signingConfig = signingConfig
                            task.apk = apkFile
                            task.jiagu = jiagu
                    }
            }
        }
    }
}