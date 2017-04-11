package ua.lviv.pancha.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Vasyl on 11.04.2017.
 */
public class PageGenerator
{
    private static final String HTML_DIR = "templates";
    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    private PageGenerator()
    {
        cfg = new Configuration(Configuration.getVersion());
    }

    public static PageGenerator instance()
    {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data)
    {
        Writer stream = new StringWriter();
        try
        {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
