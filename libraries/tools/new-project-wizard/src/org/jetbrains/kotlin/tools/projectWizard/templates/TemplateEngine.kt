package org.jetbrains.kotlin.tools.projectWizard.templates

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.RuntimeServices
import org.apache.velocity.runtime.log.LogChute
import org.jetbrains.kotlin.tools.projectWizard.core.TaskResult
import org.jetbrains.kotlin.tools.projectWizard.core.context.WritingContext
import org.jetbrains.kotlin.tools.projectWizard.core.div
import org.jetbrains.kotlin.tools.projectWizard.core.service.FileFormattingService
import org.jetbrains.kotlin.tools.projectWizard.core.service.FileSystemWizardService
import java.io.StringWriter


interface TemplateEngine {
    fun renderTemplate(template: FileTemplateDescriptor, data: Map<String, Any?>): String

    fun WritingContext.writeTemplate(template: FileTemplate): TaskResult<Unit> {
        val formatter = service<FileFormattingService>()
        val text = renderTemplate(template.descriptor, template.data).let { text ->
            formatter.formatFile(text, template.descriptor.relativePath.fileName.toString())
        }
        return service<FileSystemWizardService>().createFile(template.rootPath / template.descriptor.relativePath, text)
    }
}

class VelocityTemplateEngine : TemplateEngine {
    override fun renderTemplate(template: FileTemplateDescriptor, data: Map<String, Any?>): String {
        val templatePath = template.templateId
        val templateText = VelocityTemplateEngine::class.java.getResource(templatePath).readText()
        val context = VelocityContext().apply {
            data.forEach { (key, value) ->
                put(key, value)
            }
        }
        return StringWriter().use { writer ->
            runVelocityActionWithoutLogging { Velocity.evaluate(context, writer, "", templateText) }
            writer.toString()
        }
    }

    private fun runVelocityActionWithoutLogging(action: () -> Unit) {
        val initialLogger = Velocity.getProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM)
        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, DO_NOTHING_VELOCITY_LOGGER)
        action()
        if (initialLogger != null) {
            Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, initialLogger)
        }
    }

    companion object {
        private val DO_NOTHING_VELOCITY_LOGGER = object : LogChute {
            override fun isLevelEnabled(level: Int): Boolean = false
            override fun init(rs: RuntimeServices?) = Unit
            override fun log(level: Int, message: String?) = Unit
            override fun log(level: Int, message: String?, t: Throwable?) = Unit
        }
    }
}