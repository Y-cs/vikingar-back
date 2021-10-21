package self.vikingar.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import self.vikingar.model.dto.document.DocumentDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 17:45
 * @Description:
 **/
public class FreemarkerSupport {

    private final Configuration configuration;

    private String charsetEncoding = "utf-8";

    private final String templatePath;

    private final String templateName;

    private String writeFilePath;


    public FreemarkerSupport(String templatePath, String templateName) {
        this.templatePath = templatePath;
        this.templateName = templateName;
        this.configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    }

    public void writeHtml(DocumentDto document) throws IOException, TemplateException {
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding(charsetEncoding);
        Template template = configuration.getTemplate(templateName);
        try (Writer out = new FileWriter(writeFilePath)) {
            template.process(document, out);
        }
    }

    public void setCharsetEncoding(String charsetEncoding) {
        this.charsetEncoding = charsetEncoding;
    }

    public void setWriteFilePath(String writeFilePath) {
        this.writeFilePath = writeFilePath;
    }
}
