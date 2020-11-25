package com.zgjy.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class JiaguTask extends DefaultTask {
    Jiagu jiagu
    SigningConfig signingConfig
    File apk

    JiaguTask() {
        group = 'JiaguTask'
    }

    @TaskAction
    void run() {
        project.exec { it ->
            it.commandLine('java', '-jar', jiagu.toolsPath, '-login', jiagu.userName, jiagu.password)
        }

        project.exec {
            it.commandLine('java', '-jar', jiagu.toolsPath, '-importsign', signingConfig.storeFile,
                    signingConfig.storePassword, signingConfig.keyAlias, signingConfig.keyPassword)
        }

        project.exec {
            it.commandLine('java', '-jar', jiagu.toolsPath, '-jiagu', apk.absolutePath,
                    apk.parent, '-autosign')
        }
    }
}